package com.shipping.gts.pb.pbshipping;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class CommodityDetail extends Fragment {
    private ProgressDialog pDialog = null;
    private Spinner categorySpinner, productSpinner;
    private Button showResult;
    private String product = "Select Product", toCountryCode = GlobalDataHolder.getToCountryCode();
    private final int[] productArrayIds = { R.array.cat0, R.array.cat1,R.array.cat2,R.array.cat3,R.array.cat4,R.array.cat5,R.array.cat6,
            R.array.cat7,R.array.cat8,R.array.cat9,R.array.cat10,R.array.cat11,R.array.cat12,R.array.cat13,R.array.cat14,R.array.cat15,
            R.array.cat16,R.array.cat17,R.array.cat18,R.array.cat19,R.array.cat20,R.array.cat21,R.array.cat22,R.array.cat23,R.array.cat24
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.commodity_details, container, false);
        clearData();
        categorySpinner = (Spinner)rootView.findViewById(R.id.categorySpinner);
        productSpinner = (Spinner)rootView.findViewById(R.id.productSpinner);
        showResult = (Button)rootView.findViewById(R.id.ProceedCommidityDescription);
        initializeSpinners();
        setResultListner();
        return rootView;
    }
    private void setResultListner() {
        showResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(product.equals("Select Product")))
                    fetchResult();
            }
        });
    }
    private void clearData() {
        Log.e("Resstrictons","cleared");
        GlobalDataHolder.setItemdesc("Any Item");
        GlobalDataHolder.setcRestriction("No");
        GlobalDataHolder.setiRestriction("No");
        GlobalDataHolder.seteRestriction("No");
        GlobalDataHolder.setDeliveryCommitment("No Commitment");
        GlobalDataHolder.setpUnit("US Dollar");
        GlobalDataHolder.setDutyTax(0.0f);
        GlobalDataHolder.setSalesTax(0.0f);
        GlobalDataHolder.clearOtherTax();
    }
    private void fetchResult(){
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
        //cm.getActiveNetworkInfo() != null
        if (cm.getActiveNetworkInfo() != null)
            new GetHSCode().execute();
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

    private void initializeSpinners() {
        ArrayAdapter<CharSequence> catAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.categorylist, android.R.layout.simple_spinner_item);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(catAdapter);
        ArrayAdapter<CharSequence> prodAapter = ArrayAdapter.createFromResource(getActivity(), R.array.cat0, android.R.layout.simple_spinner_item);
        prodAapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSpinner.setAdapter(prodAapter);
        productSpinner.setClickable(false);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(getActivity(), productArrayIds[position], android.R.layout.simple_spinner_item);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                productSpinner.setAdapter(dataAdapter);
                if(position > 0) {
                    productSpinner.setClickable(true);
                    productSpinner.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return false;
                        }
                    });
                }
                else {
                    productSpinner.setClickable(false);
                    productSpinner.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_UP) {
                                Toast.makeText(getActivity(), "Select Valid Category", Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    class GetHSCode extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params) {
            try {
                testgethscode();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return null;
        }
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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            /*try {

                //TODO: PERFORM ReSULT
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
            FragmentChangeListner listner = (FragmentChangeListner)getActivity();
            listner.refreshView();
        }

    }

    private void testgethscode() throws IOException, XmlPullParserException {
        String gethsurl = "http://www.dutycalculator.com/api2.1/2bac6dba1354599a/get-hscode?";
        List<NameValuePair> hscodeParams = new LinkedList<>();
        hscodeParams.add(new BasicNameValuePair("to", toCountryCode));
        hscodeParams.add(new BasicNameValuePair("desc[0]", product));
        hscodeParams.add(new BasicNameValuePair("detailed_result", "1"));
        String paramString = URLEncodedUtils.format(hscodeParams, "utf-8");
        gethsurl += paramString;
        Log.e("gethsurl", gethsurl);
        URL mgethsurl = new URL(gethsurl);
        HttpURLConnection conn = (HttpURLConnection) mgethsurl.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        InputStream stream = conn.getInputStream();
        parse(stream);

    }

    public void parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser myParser = Xml.newPullParser();
            myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            myParser.setInput(in, null);
            myParser.nextTag();
            int event;
            String text = " ";
            String description, taxtype = null;
            try {
                event = myParser.getEventType();

                while (event != XmlPullParser.END_DOCUMENT) {
                    String name = myParser.getName();

                    switch (event) {
                        case XmlPullParser.START_TAG: {
                            if (name.equals("duty")) {
                                taxtype = "Duty Tax";
                            }
                            else if (name.equals("sales-tax")) {
                                taxtype = myParser.getAttributeValue(0);
                            }
                            else if (name.equals("tax")) {
                                taxtype = myParser.getAttributeValue(0);
                            }
                            break;
                        }
                        case XmlPullParser.TEXT: {
                            text = " ";
                            text = myParser.getText();
                            Log.e("text", text);
                            break;
                        }
                        case XmlPullParser.END_TAG:
                        {
                            if (name.equals("duty")) {
                                Log.i("duty", text);
                                if(text != " ")
                                    GlobalDataHolder.setDutyTax(Float.valueOf(text.substring(0,text.indexOf('%'))));
                                text = " ";
                            } else if (name.equals("item")) {
                                Log.i("item", text);
                                if(text != " ")
                                    GlobalDataHolder.setItemdesc(text);
                                text = " ";
                            } else if (name.equals("sales-tax")) {
                                Log.i("sales-tax", text);
                                if(text != " ")
                                    if(text != " ")
                                        GlobalDataHolder.setSalesTax(Float.valueOf(text.substring(0,text.indexOf('%'))));
                                text = " ";
                            } else if (name.equals("tax")) {
                                Log.i(taxtype, text);
                                if(text != " ")
                                    if(text != " "){
                                        if(text.contains("%")){
                                            GlobalDataHolder.setOtherTax(Float.valueOf(text.substring(0,text.indexOf('%'))));
                                            /*if(text.indexOf('%') == 1 || text.indexOf('%') == 2)
                                                GlobalDataHolder.setOtherTax(Float.valueOf(text.substring(0,text.indexOf('%'))));
                                            else if(text.charAt(text.indexOf('%')-2) == ' ')
                                                GlobalDataHolder.setOtherTax(Float.valueOf(text.substring(text.indexOf('%')-1,text.indexOf('%'))));
                                            else
                                                GlobalDataHolder.setOtherTax(Float.valueOf(text.substring(text.indexOf('%')-2,text.indexOf('%'))));*/
                                        }
                                    }
                                text = " ";
                            } else if (name.equals("import-restrictions")) {
                                Log.i("import-restrictions", text);
                                if(text != " ")
                                    GlobalDataHolder.setiRestriction(text);
                                text = " ";
                            } else if (name.equals("export-restrictions")) {
                                Log.i("export-restrictions", text);
                                if(text != " ")
                                    GlobalDataHolder.seteRestriction(text);
                                text = " ";
                            } else if (name.equals("carrier-restrictions")) {
                                Log.i("carrier-restrictions", text);
                                if(text != " ")
                                    GlobalDataHolder.setcRestriction(text);
                                text = " ";
                            } else {
                            }
                            break;
                        }
                    }
                    event = myParser.next();
                }
                Log.i("globalvalues",GlobalDataHolder.getProductCost()+" "+GlobalDataHolder.getShippingCost()+" "+GlobalDataHolder.getDutyTax()+" "+GlobalDataHolder.getSalesTax()+" "+GlobalDataHolder.getOtherTax());
                Log.i("globalvalues",GlobalDataHolder.getiRestriction()+" "+GlobalDataHolder.geteRestriction()+" "+GlobalDataHolder.getcRestriction()+" "+GlobalDataHolder.getItemdesc()+" "+GlobalDataHolder.getDeliveryCommitment());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            in.close();
        }
    }

}
