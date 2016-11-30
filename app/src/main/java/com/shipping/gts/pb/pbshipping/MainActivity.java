package com.shipping.gts.pb.pbshipping;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
//import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends FragmentActivity implements FragmentChangeListner {

    private FromDetails fromDetails;
    private JSONObject jobj;
    private ProgressDialog pDialog = null;
    public static JSONArray shippingCountry;
    public static HashMap<String, String> countryCodePairs;
    private boolean allDone = false;
    @Override
    public void replaceFragment(Fragment fragment) {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FragFrame, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }

    @Override
    public void refreshView() {
        Intent intent = new Intent(this, YourShipment.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }else{
            setContentView(R.layout.activity_main);
            getOauth();
          //  clearData();
            countryCodePairs = new HashMap<>();
            try {
                shippingCountry = new JSONArray(GlobalDataHolder.getData());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < shippingCountry.length(); i++) {
                try {
                    JSONObject object = (JSONObject) shippingCountry.get(i);
                    countryCodePairs.put(object.getString("countryName"), object.getString("countryCode"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            fromDetails = new FromDetails();
            getSupportFragmentManager().beginTransaction().add(R.id.FragFrame, fromDetails).commit();
        }
    }
/*
    private void clearData() {
        GlobalDataHolder.setItemdesc("Any Item");
        GlobalDataHolder.setFromCountrytCode("US");
        GlobalDataHolder.setFrom(null);
        GlobalDataHolder.setToCountryCode("US");
        GlobalDataHolder.setTo(null);
        GlobalDataHolder.setcRestriction("No");
        GlobalDataHolder.setiRestriction("No");
        GlobalDataHolder.seteRestriction("No");
        GlobalDataHolder.setProductCost(0);
        GlobalDataHolder.setPrice("0");
        GlobalDataHolder.setShippingCost(0.0f);
        GlobalDataHolder.setDeliveryCommitment("No Commitment");
        GlobalDataHolder.setpUnit("US Dollar");
        GlobalDataHolder.setDutyTax(0.0f);
        GlobalDataHolder.setSalesTax(0.0f);
        GlobalDataHolder.setOtherTax(0.0f);
    }
*/

    public void getOauth(){
        Log.i("netconnect",""+isNetworkConnected());
        if (isNetworkConnected())
            new Authentication().execute();
        else{
            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("No Internet Connection!")
                    .setMessage("Sorry! Ship Exact Requires Internet Connection to start. \n Try again Later")
                    .setPositiveButton("Close",new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            alertDialog.show();
        }
    }

    class Authentication extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            //fetch previous token
            SharedPreferences preferences = getSharedPreferences("oauth",0);
            String oauth = preferences.getString("token","BLANK");
            GlobalDataHolder.setOauth(oauth);

            //check previous token
            if(!validtoken(oauth))
            {

                //get new token
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://api-sandbox.pitneybowes.com/oauth/token");
                String json = null;
                try {
                    httppost.setHeader("Authorization", "Basic TzdnQ0d1V3JRSnZHejNmMTVoTW1DZXpBRWliTngwbzk6c0FpYW9oRlJZRW1LbHZRUg==");
                    httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                    nameValuePairs.add(new BasicNameValuePair("grant_type", "client_credentials"));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    // Execute HTTP Post Request
                    HttpResponse response = httpclient.execute(httppost);
                    InputStream in = null;
                    if (response != null) {
                        in = response.getEntity().getContent();
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    in.close();
                    json = sb.toString();
                    Log.e("aouthtoken", "" + json);

                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                }
                try {
                    jobj = new JSONObject(json);
                } catch (JSONException e) {
                    Log.e("json Excp", "" + e);
                }
            }
            else {
                allDone = true;
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Starting..");
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            if (!allDone) {
                try {
                    String oauth = jobj.getString("access_token");
                    SharedPreferences preferences = getSharedPreferences("oauth", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("token", oauth);
                    editor.commit();
                    if (GlobalDataHolder.getOauth().equals("checked")) {
                        GlobalDataHolder.setOauth(oauth);
                        fromDetails.performProceedClick();
                    } else
                        GlobalDataHolder.setOauth(oauth);
                  //  Toast.makeText(MainActivity.this, "new oauth generated "+oauth, Toast.LENGTH_SHORT).show();
                    Log.e("new oauth",oauth);
                    Log.e("token", oauth);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean validtoken(String oauth) {
        String mUrl = "https://api-sandbox.pitneybowes.com/shippingservices/v1/countries?carrier=usps&originCountryCode=US";
        boolean response = false;
        try {
            response = HttpsGetParser.jsonParser(oauth,mUrl);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return response;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}