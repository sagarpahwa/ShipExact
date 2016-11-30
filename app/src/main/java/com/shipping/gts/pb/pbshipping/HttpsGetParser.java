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

public class HttpsGetParser {

    public static boolean jsonParser(String token,String urlString) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        URL url = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(getSSLSocketFactory());
        connection.setHostnameVerifier(getHostnameVerifier());
        connection.setDoInput(true);
        connection.setDoOutput(false);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US");
        Log.e("code is ",""+connection.getResponseCode()+" for token "+token);
        if(connection.getResponseCode() == 200) {
            return  true;
        }else{
            return false;
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
