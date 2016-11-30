package com.shipping.gts.pb.pbshipping;


import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
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

public class FromDetails extends Fragment {
    private EditText addressline1, postalCode;
//    private Spinner countryspinner;
    private String country, countryCode = "US", address, pincode;
    private Button proceedImportFrom;
    private int addcurrentLength, postalcodecurrentlength, countrycodesurrentlength;
    private JSONObject requestobj = null, responseobj = null;
    private JSONArray responseErrObj = null;
    private ProgressDialog pDialog = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.from_details, container, false);
        clearData();
        initiateView(rootView);
        setListners();

        return rootView;
    }

    private void setListners() {
        proceedImportFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("values are", address + " " + pincode + " " + country);
                if (!(addcurrentLength > 0 && postalcodecurrentlength == 5)){
                    Toast.makeText(getActivity(), "Invalid Address or PostalCode", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    validateAddress();
                }
            }
        });
    }

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

    private void validateAddress() {
        /*country = getActivity().getResources().getStringArray(R.array.countries)[i];
        countryCode = MainActivity.countryCodePairs.get(country);
        */Log.e("validate", "called " + country + " " + countryCode);
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
        //cm.getActiveNetworkInfo() != null
        if (cm.getActiveNetworkInfo() != null) {
            if (GlobalDataHolder.getOauth().equals("BLANK")) {
                ((MainActivity) getActivity()).getOauth();
                GlobalDataHolder.setOauth("checked");
            } else
                new ValidateAddress().execute();
        } else {
            final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("No Internet Connection!")
                    .setMessage("Sorry! Ship Exact Requires Internet Connection to start. \n Try again Later")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            alertDialog.show();
        }
    }
    //}
    private void initiateView(View view) {

        addressline1 = (EditText) view.findViewById(R.id.addressline1);
        postalCode = (EditText) view.findViewById(R.id.PostalCode);
        //countryspinner = (Spinner) view.findViewById(R.id.countrySpinner);

        addressline1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addcurrentLength = s.toString().length();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (addcurrentLength == 0)
                    addressline1.setBackgroundColor(0x33EE6B0B);
                else {
                    addressline1.setBackgroundColor(0x00000000);
                    address = addressline1.getText().toString();
                }
            }
        });
        postalCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                postalcodecurrentlength = s.toString().length();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (postalcodecurrentlength == 0)
                    postalCode.setBackgroundColor(0x33EE6B0B);
                else{
                    postalCode.setBackgroundColor(0x00000000);
                    pincode = postalCode.getText().toString();
                }
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //countryspinner.setAdapter(adapter);

        /*countryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = (String) parent.getItemAtPosition(position);
                //countryCode = getActivity().getResources().getStringArray(R.array.countriecodes)[position];
                countryCode = MainActivity.countryCodePairs.get(country);
                Log.e(country,countryCode);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/
        proceedImportFrom = (Button) view.findViewById(R.id.ProceedImportFrom);
    }

    public void performProceedClick(){
        proceedImportFrom.performClick();
    }

    class ValidateAddress extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Initialting");
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
            responseErrObj = null;
            responseobj = null;
        }

        @Override
        protected Void doInBackground(String... params) {
            String response = null;
            try {
                requestobj = new JSONObject();
                JSONArray array = new JSONArray();
                array.put(address);
                array.put("");
                array.put("");
                requestobj.put("addressLines",array);
                requestobj.put("cityTown","");
                requestobj.put("stateProvince","");
                requestobj.put("postalCode",pincode);
                requestobj.put("countryCode",countryCode);
                requestobj.put("company","");
                requestobj.put("name","");
                requestobj.put("phone","");
                requestobj.put("email","");

                //test apis here
                /*
                String jsonstring = "{\"fromAddress\":{\"addressLines\":[\"37ExecutiveDr.\",\"\",\"\"],\"cityTown\":\"Danbury\",\"stateProvince\":\"CT\",\"postalCode\":\"06810\",\"countryCode\":\"US\",\"name\":\"\",\"company\":\"\",\"phone\":\"\",\"email\":\"\"},\"toAddress\":{\"addressLines\":[\"1Atwellrd\",\"bldg2\",\"apt302\"],\"cityTown\":\"Cooperstown\",\"stateProvince\":\"NY\",\"postalCode\":\"13326\",\"countryCode\":\"US\",\"name\":\"JamesBrother\",\"company\":\"MaryImogeneBassetHospital\",\"phone\":\"203-924-2428\",\"email\":\"johndoe@email.com\"},\"parcel\":{\"weight\":{\"unitOfMeasurement\":\"OZ\",\"weight\":1},\"dimension\":{\"unitOfMeasurement\":\"IN\",\"length\":6,\"width\":0.25,\"height\":4,\"irregularParcelGirth\":0.002}},\"rates\":[{\"carrier\":\"USPS\",\"serviceId\":\"PM\",\"parcelType\":\"PKG\",\"specialServices\":[{\"specialServiceId\":\"Ins\",\"inputParameters\":[{\"name\":\"INPUT_VALUE\",\"value\":\"50\"}]},{\"specialServiceId\":\"DelCon\",\"inputParameters\":[{\"name\":\"INPUT_VALUE\",\"value\":\"0\"}]}],\"inductionPostalCode\":\"06484\"}]}";
                URL url = new URL("https://api-sandbox.pitneybowes.com/shippingservices/v1/rates?includeDeliveryCommitment=true");
                requestobj = new JSONObject(jsonstring);*/
                //end test space

                response = HttpsPostParser.jsonParser("https://api-sandbox.pitneybowes.com/shippingservices/v1/addresses/verify",requestobj);
                if (!(response.startsWith("{"))){

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
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

            try{
                if (response != null) {
                    if (response.startsWith("{")){
                        responseErrObj = null;
                        responseobj = new JSONObject(response);
                    }
                    else{
                        responseobj = null;
                        responseErrObj = new JSONArray(response);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();

            if(responseErrObj!=null){
                GlobalDataHolder.setFrom(requestobj);
                GlobalDataHolder.setFromCountrytCode(countryCode);
                String msg = "Invalid Address or Country or Postal Code!";
                Log.e("error is",""+responseErrObj.toString());
                try {
                    JSONObject obj = responseErrObj.getJSONObject(0);
                    msg = obj.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
            else if((responseobj != null)&&(!responseobj.has("fault"))){
                responseobj.remove("residential");
                responseobj.remove("status");
                GlobalDataHolder.setFromCountrytCode(countryCode);
              //  Toast.makeText(getActivity(), ""+responseobj.toString(), Toast.LENGTH_SHORT).show();
                Log.e("Response from",responseobj.toString());
                GlobalDataHolder.setFrom(responseobj);
               /* try {
                    JSONArray array = new JSONArray();
                    array = (JSONArray) responseobj.get("addressLines");
                    address = array.getString(0);
                    city = responseobj.getString("cityTown");
                    pincode = responseobj.getString("postalCode");
                    countryCode = responseobj.getString("countryCode");
                    //TODO: to next Fragment
                    Toast.makeText(getActivity(), ""+responseobj.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("Response is",responseobj.toString());
                }
                catch (JSONException e){
                    e.printStackTrace();
                }*/
                Fragment toDetails = new ToDetails();
                FragmentChangeListner listner = (FragmentChangeListner)getActivity();
                listner.replaceFragment(toDetails);
            }


        }
    }
}