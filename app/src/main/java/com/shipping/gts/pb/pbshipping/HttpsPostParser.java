package com.shipping.gts.pb.pbshipping;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by NEX89PE on 10/24/2016.
 */

public class HttpsPostParser {

    public static String jsonParser(String urlString, JSONObject requestobj) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        URL url = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(getSSLSocketFactory());
        connection.setHostnameVerifier(getHostnameVerifier());
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + GlobalDataHolder.getOauth());
        connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US");
        String sendjson = requestobj.toString();
        Log.e("conn",connection.toString());
        DataOutputStream os = new DataOutputStream(connection.getOutputStream());
        os.write(sendjson.getBytes());
        os.flush();
        os.close();
        Log.e("code is ",""+connection.getResponseCode());
        if(connection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        }else{
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        }
    }

    private static HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                return hv.verify("api-sandbox.pitneybowes.com", session);
            }
        };
    }
    private static SSLSocketFactory getSSLSocketFactory()throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        TrustManager tm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain,String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain,String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() {return null;}
        };
        sslContext.init(null, new TrustManager[] { tm }, null);
        return sslContext.getSocketFactory();
    }


}
