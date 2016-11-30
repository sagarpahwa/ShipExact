package com.shipping.gts.pb.pbshipping;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;


public class ParcelDetail extends Fragment {

    private EditText mPrice, mWeight, mLength, mWidth, mHeight ;
    private Spinner priceUnits, weightUnits, sizeUnits;
    private String productCost = "-1", productWeight = "-1", length = "-1", width = "-1", height = "-1", pUnits = "US Dollar", wUnits = "OZ", sUnits = "IN";
    private Button proceed;
    private int productCostLength, productWeightInt = 0, lengthInt = 0, widthInt = 0, heigthInt = 0;
    private JSONObject requestobj = null, responseobj = null;
    private JSONArray responseErrObj = null;
    private ProgressDialog pDialog = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.parcel_details, container, false);
        clearData();
        mPrice = (EditText)rootView.findViewById(R.id.ProductCost);
        mWeight = (EditText)rootView.findViewById(R.id.ProductWeight);
        mLength = (EditText)rootView.findViewById(R.id.Length);
        mWidth = (EditText)rootView.findViewById(R.id.Width);
        mHeight= (EditText)rootView.findViewById(R.id.Height);
        priceUnits = (Spinner)rootView.findViewById(R.id.PriceUnit);
        weightUnits = (Spinner)rootView.findViewById(R.id.ProductWeightUnit);
        sizeUnits = (Spinner)rootView.findViewById(R.id.SizeUnit);
        proceed = (Button)rootView.findViewById(R.id.SubmitDetails);
        initializeListners();

        return rootView;
    }
    private void clearData() {
        GlobalDataHolder.setProductCost(0);
        GlobalDataHolder.setPrice("0");
        GlobalDataHolder.setShippingCost(0.0f);
        GlobalDataHolder.setpUnit("US Dollar");
    }

    private void initializeListners() {
        mPrice.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productCostLength = s.toString().length();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (productCostLength == 0)
                    mPrice.setBackgroundColor(0x33EE6B0B);
                else {
                    mPrice.setBackgroundColor(0x00000000);
                    productCost = mPrice.getText().toString();
                }
            }
        });
        mWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(mWeight.length()>0) {
                    if (wUnits.equals("GM"))
                        productWeightInt = (int) (0 + (Integer.valueOf(mWeight.getText().toString())) / 28.3495);
                    else
                        productWeightInt = 0 + Integer.valueOf(mWeight.getText().toString());
                    if ((productWeightInt < 1) || (productWeightInt > 1120))
                        mWeight.setBackgroundColor(0x33EE6B0B);
                    else {
                        mWeight.setBackgroundColor(0x00000000);
                        productWeight = mWeight.getText().toString();
                    }
                }
            }
        });
        mLength.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(mLength.length()>0) {
                    if (sUnits.equals("IN"))
                        lengthInt = 0 + Integer.valueOf(mLength.getText().toString());
                    else
                        lengthInt = (int) (0 + (Integer.valueOf(mLength.getText().toString())) / 2.54);
                    if ((lengthInt < 5) || (lengthInt > 98))
                        mLength.setBackgroundColor(0x33EE6B0B);
                    else {
                        mLength.setBackgroundColor(0x00000000);
                        length = mLength.getText().toString();
                    }
                }
            }
        });
        mWidth.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(mWidth.length()>0) {
                    if (sUnits.equals("IN"))
                        widthInt = 0 + Integer.valueOf(mWidth.getText().toString());
                    else
                        widthInt = (int) (0 + (Integer.valueOf(mWidth.getText().toString())) / 2.54);
                    if ((widthInt < 1) || (widthInt > 47))
                        mWidth.setBackgroundColor(0x33EE6B0B);
                    else {
                        mWidth.setBackgroundColor(0x00000000);
                        width = mWidth.getText().toString();
                    }
                }
            }
        });
        mHeight.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(mHeight.length()>0) {
                    if (sUnits.equals("IN"))
                        heigthInt = 0 + Integer.valueOf(mHeight.getText().toString());
                    else
                        heigthInt = (int) (0 + (Integer.valueOf(mHeight.getText().toString())) / 2.54);
                    if ((heigthInt < 4) || (heigthInt > 50))
                        mHeight.setBackgroundColor(0x33EE6B0B);
                    else {
                        mHeight.setBackgroundColor(0x00000000);
                        height = mHeight.getText().toString();
                    }
                }
            }
        });

        ArrayAdapter<CharSequence> priceAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.PriceUnits, android.R.layout.simple_spinner_item);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceUnits.setAdapter(priceAdapter);
        priceUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pUnits = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.WeightUnits, android.R.layout.simple_spinner_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightUnits.setAdapter(weightAdapter);
        weightUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    wUnits = (position == 0)?"OZ":"GM";
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.SizeUnits, android.R.layout.simple_spinner_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeUnits.setAdapter(sizeAdapter);
        sizeUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sUnits = (position == 0)?"IN":"CM";
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(productCost == null||productWeight == null||length == null||width == null||height == null)) {    //check max and min

                    if ((productWeightInt > 1120) || (productWeightInt < 1))
                        Toast.makeText(getActivity(), "Invalid parcel weight", Toast.LENGTH_SHORT).show();
                    else if ((lengthInt < widthInt))
                        Toast.makeText(getActivity(), "Width must be less Length", Toast.LENGTH_SHORT).show();
                    else if ((lengthInt > 98) || (lengthInt < 5))
                        Toast.makeText(getActivity(), "Invalid parcel length", Toast.LENGTH_SHORT).show();
                    else if ((widthInt > 47) || (widthInt < 1))
                        Toast.makeText(getActivity(), "Invalid parcel width", Toast.LENGTH_SHORT).show();
                    else if((heigthInt > 50)||(heigthInt < 4))
                        Toast.makeText(getActivity(), "Invalid parcel height", Toast.LENGTH_SHORT).show();
                    else if(108 < lengthInt+(2*(widthInt+heigthInt)))
                        Toast.makeText(getActivity(), "Parcel size too big", Toast.LENGTH_SHORT).show();
                    else if (productCost.equals("-1"))
                        Toast.makeText(getActivity(), "invalid product cost", Toast.LENGTH_SHORT).show();
                    else
                        calculateShipment();

                }
            }
        });
    }

    private void calculateShipment(){
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
        //cm.getActiveNetworkInfo() != null
        if (cm.getActiveNetworkInfo() != null)
            new GetShippingPrice().execute();
        else{
            final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
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

    class GetShippingPrice extends AsyncTask<String,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Initialting");
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            String response = null;
            try {

                JSONObject weightdesc = new JSONObject();
                weightdesc.put("weight",""+productWeightInt);
                //weightdesc.put("unitOfMeasurement",wUnits);
                weightdesc.put("unitOfMeasurement","OZ");

                JSONObject dimensiondesc = new JSONObject();
                dimensiondesc.put("length",""+lengthInt);
                dimensiondesc.put("width",""+widthInt);
                dimensiondesc.put("height",""+heigthInt);
                //dimensiondesc.put("unitOfMeasurement",sUnits);
                dimensiondesc.put("unitOfMeasurement","IN");
                dimensiondesc.put("irregularParcelGirth",""+(lengthInt+(2*(widthInt+heigthInt))));

                JSONObject parceldesc = new JSONObject();
                parceldesc.put("weight",weightdesc);
                parceldesc.put("dimension",dimensiondesc);

                String jsonstring = "";

                if(!GlobalDataHolder.getToCountryCode().equals(GlobalDataHolder.getFromCountrytCode())) {
                    //jsonstring = "{\"carrier\":\"usps\",\"inductionPostalCode\":\"06484\",\"parcelType\":\"PKG\",\"rateTypeId\":\"commercialBase\",\"serviceId\":\"PMI\",\"specialServices\":[{\"fee\":0,\"inputParameters\":[{\"name\":\"INPUT_VALUE\",\"value\":\"50\"}],\"specialServiceId\":\"Ins\"}]}";
                    jsonstring = "{\"carrier\":\"usps\",\"inductionPostalCode\":\"06484\",\"parcelType\":\"PKG\",\"rateTypeId\":\"commercialBase\",\"serviceId\":\"PMI\",\"specialServices\":[]}";
                }
                else
                    jsonstring = "{\"carrier\":\"USPS\",\"serviceId\":\"PM\",\"parcelType\":\"PKG\",\"specialServices\":[{\"specialServiceId\":\"Ins\",\"inputParameters\":[{\"name\":\"INPUT_VALUE\",\"value\":\"50\"}]},{\"specialServiceId\":\"DelCon\",\"inputParameters\":[{\"name\":\"INPUT_VALUE\",\"value\":\"0\"}]}],\"inductionPostalCode\":\"06484\"}";
                JSONObject ratesobj = new JSONObject(jsonstring);
                JSONArray ratesdesc = new JSONArray();
                ratesdesc.put(0,ratesobj);

                requestobj = new JSONObject();
                requestobj.put("fromAddress",GlobalDataHolder.getFrom());
                requestobj.put("toAddress",GlobalDataHolder.getTo());
                requestobj.put("parcel",parceldesc);
                requestobj.put("rates",ratesdesc);
                Log.i("requestedJson",requestobj.toString());

                //requestobj = new JSONObject(jsonstring);



                //test apis here

                /*

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

*/
                //end test space
                response = HttpsPostParser.jsonParser("https://api-sandbox.pitneybowes.com/shippingservices/v1/rates?includeDeliveryCommitment=true",requestobj);

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
                    if (response.startsWith("{")) {
                        responseErrObj = null;
                        responseobj = new JSONObject(response);
                        JSONArray ratesArray = (JSONArray) responseobj.getJSONArray("rates");
                        float charges = Float.valueOf(ratesArray.getJSONObject(0).getString("baseCharge"));
                        Log.e("Shiping",""+charges);
                        GlobalDataHolder.setShippingCost(charges);

                        String deliveryCommitment = ratesArray.getJSONObject(0).getJSONObject("deliveryCommitment").getString("estimatedDeliveryDateTime");
                        GlobalDataHolder.setDeliveryCommitment(deliveryCommitment);
                        Log.e("deliveryCommitment",deliveryCommitment);

                        GlobalDataHolder.setpUnit(pUnits);
                        GlobalDataHolder.setProductCost(Integer.valueOf(productCost));
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
                String msg = "Invalid Address or Country or Postal Code!";
                Log.e("error is",""+responseErrObj.toString());
                try {
                    JSONObject obj = responseErrObj.getJSONObject(0);
                    msg = obj.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                return;
            }
            else if((responseobj != null)&&(!responseobj.has("fault"))){
                String price = null;
  //              Toast.makeText(getActivity(), ""+responseobj.toString(), Toast.LENGTH_SHORT).show();
                Log.e("Response from",responseobj.toString());
                GlobalDataHolder.setPrice(price);

                if(!GlobalDataHolder.getToCountryCode().equals("US")) {
                    Fragment nextStep = new CommodityDetail();
                    FragmentChangeListner listner = (FragmentChangeListner) getActivity();
                    listner.replaceFragment(nextStep);
                }
                else{
                    GlobalDataHolder.setItemdesc("Any Item");
                    FragmentChangeListner listner = (FragmentChangeListner)getActivity();
                    listner.refreshView();
                }
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
            }
        }
    }
}
