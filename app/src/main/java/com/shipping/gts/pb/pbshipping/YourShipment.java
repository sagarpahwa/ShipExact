package com.shipping.gts.pb.pbshipping;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YourShipment extends AppCompatActivity {

    private TextView item, cost, duty, insurance, tax, otherTax, shipment, total, totalTextView,  arrival, isRestricted, irestriction, erestriction, crestriction;
    private LinearLayout costLayout,shipLayout, insuranceLayout, dutyLayout, sTaxLayout, oTaxLayout, IRLayout, ERLayout, CRLayout;
    String ir = GlobalDataHolder.getiRestriction(), er = GlobalDataHolder.geteRestriction(), cr = GlobalDataHolder.getcRestriction();
    private Button done,shipMore;
    float floatcost = GlobalDataHolder.getProductCost(), ship = GlobalDataHolder.getShippingCost(), duties = GlobalDataHolder.getDutyTax(),
            sTaxes = GlobalDataHolder.getSalesTax(), oTaxes = GlobalDataHolder.getOtherTax(), floatinsurance = floatcost/50;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        clearData();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_shipment);
        initiateView();

        setData();

        setListners();

        setTitle("Ship Exact");
    }

    private void initiateView() {

        //layouts

        costLayout = (LinearLayout)findViewById(R.id.costLayout);
        shipLayout = (LinearLayout)findViewById(R.id.shiptLayout);
        insuranceLayout = (LinearLayout)findViewById(R.id.insuranceLayout);
        dutyLayout = (LinearLayout)findViewById(R.id.dutyLayout);
        sTaxLayout = (LinearLayout)findViewById(R.id.sTaxLayout);
        oTaxLayout = (LinearLayout)findViewById(R.id.oTaxLayout);
        IRLayout = (LinearLayout)findViewById(R.id.IRayout);
        ERLayout = (LinearLayout)findViewById(R.id.ERLayout);
        CRLayout = (LinearLayout)findViewById(R.id.CRLayout);

        //visible

        item = (TextView)findViewById(R.id.ItemDesc);
        total = (TextView)findViewById(R.id.TotalChargeDesc);
        totalTextView = (TextView)findViewById(R.id.totalTextView);
        arrival = (TextView)findViewById(R.id.arrivalDesc);

        //invisible restrictions

        irestriction = (TextView)findViewById(R.id.iRestrictionsDesc);
        erestriction = (TextView)findViewById(R.id.eRestrictionsDesc);
        crestriction = (TextView)findViewById(R.id.RestrictionsDesc);

        //invisible costs

        cost = (TextView)findViewById(R.id.CostDesc);
        shipment = (TextView)findViewById(R.id.ShipingDesc);
        insurance = (TextView)findViewById(R.id.InsuranceDesc);
        duty = (TextView)findViewById(R.id.DutyDesc);
        tax = (TextView)findViewById(R.id.TaxDesc);
        otherTax = (TextView)findViewById(R.id.OtherTaxDesc);

        //invisible IsRestricted

        isRestricted = (TextView)findViewById(R.id.IsRistricted);

        done = (Button)findViewById(R.id.ExitApp);
        shipMore = (Button)findViewById(R.id.ShipMore);
    }

    private void setData() {
        item.setText(GlobalDataHolder.getItemdesc());

        floatcost = GlobalDataHolder.getProductCost();
        ship = GlobalDataHolder.getShippingCost();
        duties = GlobalDataHolder.getDutyTax();
        sTaxes = GlobalDataHolder.getSalesTax();
        oTaxes = GlobalDataHolder.getOtherTax();

        floatinsurance = 0;

        float totalCost = (floatcost + ship + floatinsurance + ( (floatcost + ship + floatinsurance) * (duties + sTaxes + oTaxes) /100) );
        String totalCostString = totalCost + " " + GlobalDataHolder.getpUnit();

        total.setText(totalCostString);

        arrival.setText(GlobalDataHolder.getDeliveryCommitment());

        if(!(ir.equals("No")&&er.equals("No")&&cr.equals("No"))) {
            isRestricted = (TextView)findViewById(R.id.IsRistricted);
            isRestricted.setVisibility(View.VISIBLE);
        }
    }

    private void setListners()  {
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCostDetails();
                hideRestrictions();
            }
        });
        totalTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCostDetails();
                hideRestrictions();
            }
        });
        isRestricted = (TextView)findViewById(R.id.IsRistricted);
        isRestricted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRestrictions();
                hideCostDetails();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);*/
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });

        shipMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YourShipment.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showRestrictions(){
        Log.e("restricted","entered");
    /*    irestriction = (TextView)findViewById(R.id.iRestrictionsDesc);
        erestriction = (TextView)findViewById(R.id.eRestrictionsDesc);
        crestriction = (TextView)findViewById(R.id.RestrictionsDesc);
*/
        if(!ir.equals("No")){
            irestriction.setText(GlobalDataHolder.getiRestriction());
            IRLayout.setVisibility(View.VISIBLE);
            Log.e("Irestricted",GlobalDataHolder.getiRestriction());
        }
        if(!er.equals("No")){
            erestriction.setText(GlobalDataHolder.geteRestriction());
            ERLayout.setVisibility(View.VISIBLE);
            Log.e("Erestricted",GlobalDataHolder.geteRestriction());
        }
        if(!cr.equals("No")){
            crestriction.setText(GlobalDataHolder.getcRestriction());
            CRLayout.setVisibility(View.VISIBLE);
            Log.e("Crestricted",GlobalDataHolder.getcRestriction());
        }
    }

    private void hideRestrictions(){
   /*     irestriction = (TextView)findViewById(R.id.iRestrictionsDesc);
        erestriction = (TextView)findViewById(R.id.eRestrictionsDesc);
        crestriction = (TextView)findViewById(R.id.RestrictionsDesc);
*/
        IRLayout.setVisibility(View.GONE);
        ERLayout.setVisibility(View.GONE);
        CRLayout.setVisibility(View.GONE);

    }

    private void showCostDetails(){
     /*   cost = (TextView)findViewById(R.id.CostDesc);
        shipment = (TextView)findViewById(R.id.ShipingDesc);
        insurance = (TextView)findViewById(R.id.InsuranceDesc);
        duty = (TextView)findViewById(R.id.DutyDesc);
        tax = (TextView)findViewById(R.id.TaxDesc);
        otherTax = (TextView)findViewById(R.id.OtherTaxDesc);
*/
        costLayout.setVisibility(View.VISIBLE);
        cost.setText(floatcost+ " " +GlobalDataHolder.getpUnit());
        insuranceLayout.setVisibility(View.VISIBLE);
        insurance.setText(floatinsurance+ " " +GlobalDataHolder.getpUnit());
        shipLayout.setVisibility(View.VISIBLE);
        shipment.setText(ship+ " " +GlobalDataHolder.getpUnit());
        Log.e("details","entered");
        if(GlobalDataHolder.getDutyTax() > 0){
            dutyLayout.setVisibility(View.VISIBLE);
            duty.setText(duties+ " %");
            Log.e("duties",duties+ " %");
        }
        if(GlobalDataHolder.getSalesTax() > 0){
            sTaxLayout.setVisibility(View.VISIBLE);
            tax.setText(sTaxes+ " %");
            Log.e("salestaxes",sTaxes+ " %");
        }
        if(GlobalDataHolder.getOtherTax() > 0){
            oTaxLayout.setVisibility(View.VISIBLE);
            otherTax.setText(oTaxes+ " %");
            Log.e("othertaxes",oTaxes+ " %");
        }
    }

    private void hideCostDetails(){
      /*  cost = (TextView)findViewById(R.id.CostDesc);
        shipment = (TextView)findViewById(R.id.ShipingDesc);
        insurance = (TextView)findViewById(R.id.InsuranceDesc);
        duty = (TextView)findViewById(R.id.DutyDesc);
        tax = (TextView)findViewById(R.id.TaxDesc);
        otherTax = (TextView)findViewById(R.id.OtherTaxDesc);
*/


        costLayout.setVisibility(View.GONE);
        insuranceLayout.setVisibility(View.GONE);
        shipLayout.setVisibility(View.GONE);
        dutyLayout.setVisibility(View.GONE);
        sTaxLayout.setVisibility(View.GONE);
        oTaxLayout.setVisibility(View.GONE);
    }
}