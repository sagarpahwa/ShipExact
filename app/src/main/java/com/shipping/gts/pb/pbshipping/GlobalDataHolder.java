package com.shipping.gts.pb.pbshipping;

import org.json.JSONObject;

/**
 * Created by NEX89PE on 10/23/2016.
 */

public class GlobalDataHolder {

    private static int productCost = 0;
    private static JSONObject from , to ;
    private static String oauth  = "BLANK";
    private static String price = "0 US Dollar";
    private static String toCountryCode = "0 US Dollar";
    private static String fromCountryCode = "0 US Dollar";
    private static float ShippingCost = (float) 0.0;
    private static String deliveryCommitment = "No Commitment";
    private static String pUnit = "US Dollar";
    private static String itemdesc = "Any Item";
    private static float dutyTax = 0, salesTax = 0, otherTax = 0, otherTaxPerKG = 0;
    private static String iRestriction = "No", eRestriction = "No", cRestriction = "No";

    public static float getOtherTaxPerKG() {
        return otherTaxPerKG;
    }

    public static void setOtherTaxPerKG(float otherTaxPerKG) {
        GlobalDataHolder.otherTaxPerKG = otherTaxPerKG;
    }

    public static String getiRestriction() {
        return iRestriction;
    }

    public static void setiRestriction(String iRestriction) {
        GlobalDataHolder.iRestriction = iRestriction;
    }

    public static String geteRestriction() {
        return eRestriction;
    }

    public static void seteRestriction(String eRestriction) {
        GlobalDataHolder.eRestriction = eRestriction;
    }

    public static String getcRestriction() {
        return cRestriction;
    }

    public static void setcRestriction(String cRestriction) {
        GlobalDataHolder.cRestriction = cRestriction;
    }

    public static float getOtherTax() {
        return otherTax;
    }

    public static void setOtherTax(float otherTax) {
        GlobalDataHolder.otherTax += otherTax;
    }

    public static float getSalesTax() {
        return salesTax;
    }

    public static void setSalesTax(float salesTax) {
        GlobalDataHolder.salesTax = salesTax;
    }

    public static float getDutyTax() {
        return dutyTax;
    }

    public static void setDutyTax(float dutyTax) {
        GlobalDataHolder.dutyTax = dutyTax;
    }

    public static String getItemdesc() {
        return itemdesc;
    }

    public static void setItemdesc(String itemdesc) {
        GlobalDataHolder.itemdesc = itemdesc;
    }

    public static int getProductCost() {
        return productCost;
    }

    public static void setProductCost(int productCost) {
        GlobalDataHolder.productCost = productCost;
    }

    public static String getpUnit() {
        return pUnit;
    }

    public static void setpUnit(String pUnit) {
        GlobalDataHolder.pUnit = pUnit;
    }


    public static String getDeliveryCommitment() {
        return deliveryCommitment;
    }

    public static void setDeliveryCommitment(String deliveryCommitment) {
        GlobalDataHolder.deliveryCommitment = deliveryCommitment;
    }

    public static float getShippingCost() {
        return ShippingCost;
    }

    public static void setShippingCost(float ShippingCost) {
        GlobalDataHolder.ShippingCost = ShippingCost;
    }

    public static String getToCountryCode() {
        return toCountryCode;
    }

    public static void setToCountryCode(String toCountryCode) {
        GlobalDataHolder.toCountryCode = toCountryCode;
    }

    public static String getPrice() {
        return price;
    }

    public static void setPrice(String mprice) {
        price = mprice;
    }

    public static void setOauth(String oauth) {
        GlobalDataHolder.oauth = oauth;
    }

    public static String getOauth() {
        return oauth;
    }
    public static String getData() {
        return data;
    }

    public static String data = "[  {    \"countryCode\": \"AC\",    \"countryName\": \"Ascension\"  },  {    \"countryCode\": \"AD\",    \"countryName\": \"Andorra\"  },  {    \"countryCode\": \"AE\",    \"countryName\": \"United Arab Emirates\"  },  {    \"countryCode\": \"AF\",    \"countryName\": \"Afghanistan\"  },  {    \"countryCode\": \"AG\",    \"countryName\": \"Antigua and Barbuda\"  },  {    \"countryCode\": \"AI\",    \"countryName\": \"Anguilla\"  },  {    \"countryCode\": \"AL\",    \"countryName\": \"Albania\"  },  {    \"countryCode\": \"AM\",    \"countryName\": \"Armenia\"  },  {    \"countryCode\": \"AO\",    \"countryName\": \"Angola\"  },  {    \"countryCode\": \"AR\",    \"countryName\": \"Argentina\"  },  {    \"countryCode\": \"AT\",    \"countryName\": \"Austria\"  },  {    \"countryCode\": \"AU\",    \"countryName\": \"Australia\"  },  {    \"countryCode\": \"AW\",    \"countryName\": \"Aruba\"  },  {    \"countryCode\": \"AZ\",    \"countryName\": \"Azerbaijan\"  },  {    \"countryCode\": \"BA\",    \"countryName\": \"Bosnia and Herzegovina\"  },  {    \"countryCode\": \"BB\",    \"countryName\": \"Barbados\"  },  {    \"countryCode\": \"BD\",    \"countryName\": \"Bangladesh\"  },  {    \"countryCode\": \"BE\",    \"countryName\": \"Belgium\"  },  {    \"countryCode\": \"BF\",    \"countryName\": \"Burkina Faso\"  },  {    \"countryCode\": \"BG\",    \"countryName\": \"Bulgaria\"  },  {    \"countryCode\": \"BH\",    \"countryName\": \"Bahrain\"  },  {    \"countryCode\": \"BI\",    \"countryName\": \"Burundi\"  },  {    \"countryCode\": \"BJ\",    \"countryName\": \"Benin\"  },  {    \"countryCode\": \"BM\",    \"countryName\": \"Bermuda\"  },  {    \"countryCode\": \"BN\",    \"countryName\": \"Brunei Darussalam\"  },  {    \"countryCode\": \"BO\",    \"countryName\": \"Bolivia, Plurinational State of\"  },  {    \"countryCode\": \"BR\",    \"countryName\": \"Brazil\"  },  {    \"countryCode\": \"BS\",    \"countryName\": \"Bahamas\"  },  {    \"countryCode\": \"BT\",    \"countryName\": \"Bhutan\"  },  {    \"countryCode\": \"BW\",    \"countryName\": \"Botswana\"  },  {    \"countryCode\": \"BY\",    \"countryName\": \"Belarus\"  },  {    \"countryCode\": \"BZ\",    \"countryName\": \"Belize\"  },  {    \"countryCode\": \"CA\",    \"countryName\": \"Canada\"  },  {    \"countryCode\": \"CD\",    \"countryName\": \"Congo, the Democratic Republic of the\"  },  {    \"countryCode\": \"CF\",    \"countryName\": \"Central African Republic\"  },  {    \"countryCode\": \"CG\",    \"countryName\": \"Congo\"  },  {    \"countryCode\": \"CH\",    \"countryName\": \"Switzerland\"  },  {    \"countryCode\": \"CI\",    \"countryName\": \"Côte d'Ivoire\"  },  {    \"countryCode\": \"CL\",    \"countryName\": \"Chile\"  },  {    \"countryCode\": \"CM\",    \"countryName\": \"Cameroon\"  },  {    \"countryCode\": \"CN\",    \"countryName\": \"China\"  },  {    \"countryCode\": \"CO\",    \"countryName\": \"Colombia\"  },  {    \"countryCode\": \"CR\",    \"countryName\": \"Costa Rica\"  },  {    \"countryCode\": \"CU\",    \"countryName\": \"Cuba\"  },  {    \"countryCode\": \"CV\",    \"countryName\": \"Cape Verde\"  },  {    \"countryCode\": \"CW\",    \"countryName\": \"Curaçao\"  },  {    \"countryCode\": \"CY\",    \"countryName\": \"Cyprus\"  },  {    \"countryCode\": \"CZ\",    \"countryName\": \"Czech Republic\"  },  {    \"countryCode\": \"DE\",    \"countryName\": \"Germany\"  },  {    \"countryCode\": \"DJ\",    \"countryName\": \"Djibouti\"  },  {    \"countryCode\": \"DK\",    \"countryName\": \"Denmark\"  },  {    \"countryCode\": \"DM\",    \"countryName\": \"Dominica\"  },  {    \"countryCode\": \"DO\",    \"countryName\": \"Dominican Republic\"  },  {    \"countryCode\": \"DZ\",    \"countryName\": \"Algeria\"  },  {    \"countryCode\": \"EC\",    \"countryName\": \"Ecuador\"  },  {    \"countryCode\": \"EE\",    \"countryName\": \"Estonia\"  },  {    \"countryCode\": \"EG\",    \"countryName\": \"Egypt\"  },  {    \"countryCode\": \"ER\",    \"countryName\": \"Eritrea\"  },  {    \"countryCode\": \"ES\",    \"countryName\": \"Spain\"  },  {    \"countryCode\": \"ET\",    \"countryName\": \"Ethiopia\"  },  {    \"countryCode\": \"FI\",    \"countryName\": \"Finland\"  },  {    \"countryCode\": \"FJ\",    \"countryName\": \"Fiji\"  },  {    \"countryCode\": \"FK\",    \"countryName\": \"Falkland Islands (Malvinas)\"  },  {    \"countryCode\": \"FO\",    \"countryName\": \"Faroe Islands\"  },  {    \"countryCode\": \"FR\",    \"countryName\": \"France\"  },  {    \"countryCode\": \"GA\",    \"countryName\": \"Gabon\"  },  {    \"countryCode\": \"GB\",    \"countryName\": \"United Kingdom\"  },  {    \"countryCode\": \"GD\",    \"countryName\": \"Grenada\"  },  {    \"countryCode\": \"GE\",    \"countryName\": \"Georgia\"  },  {    \"countryCode\": \"GF\",    \"countryName\": \"French Guiana\"  },  {    \"countryCode\": \"GH\",    \"countryName\": \"Ghana\"  },  {    \"countryCode\": \"GI\",    \"countryName\": \"Gibraltar\"  },  {    \"countryCode\": \"GL\",    \"countryName\": \"Greenland\"  },  {    \"countryCode\": \"GM\",    \"countryName\": \"Gambia\"  },  {    \"countryCode\": \"GN\",    \"countryName\": \"Guinea\"  },  {    \"countryCode\": \"GP\",    \"countryName\": \"Guadeloupe\"  },  {    \"countryCode\": \"GQ\",    \"countryName\": \"Equatorial Guinea\"  },  {    \"countryCode\": \"GR\",    \"countryName\": \"Greece\"  },  {    \"countryCode\": \"GT\",    \"countryName\": \"Guatemala\"  },  {    \"countryCode\": \"GW\",    \"countryName\": \"Guinea-Bissau\"  },  {    \"countryCode\": \"GY\",    \"countryName\": \"Guyana\"  },  {    \"countryCode\": \"HK\",    \"countryName\": \"Hong Kong\"  },  {    \"countryCode\": \"HN\",    \"countryName\": \"Honduras\"  },  {    \"countryCode\": \"HR\",    \"countryName\": \"Croatia\"  },  {    \"countryCode\": \"HT\",    \"countryName\": \"Haiti\"  },  {    \"countryCode\": \"HU\",    \"countryName\": \"Hungary\"  },  {    \"countryCode\": \"ID\",    \"countryName\": \"Indonesia\"  },  {    \"countryCode\": \"IE\",    \"countryName\": \"Ireland\"  },  {    \"countryCode\": \"IL\",    \"countryName\": \"Israel\"  },  {    \"countryCode\": \"IN\",    \"countryName\": \"India\"  },  {    \"countryCode\": \"IQ\",    \"countryName\": \"Iraq\"  },  {    \"countryCode\": \"IR\",    \"countryName\": \"Iran, Islamic Republic of\"  },  {    \"countryCode\": \"IS\",    \"countryName\": \"Iceland\"  },  {    \"countryCode\": \"IT\",    \"countryName\": \"Italy\"  },  {    \"countryCode\": \"JM\",    \"countryName\": \"Jamaica\"  },  {    \"countryCode\": \"JO\",    \"countryName\": \"Jordan\"  },  {    \"countryCode\": \"JP\",    \"countryName\": \"Japan\"  },  {    \"countryCode\": \"KE\",    \"countryName\": \"Kenya\"  },  {    \"countryCode\": \"KG\",    \"countryName\": \"Kyrgyzstan\"  },  {    \"countryCode\": \"KH\",    \"countryName\": \"Cambodia\"  },  {    \"countryCode\": \"KI\",    \"countryName\": \"Kiribati\"  },  {    \"countryCode\": \"KM\",    \"countryName\": \"Comoros\"  },  {    \"countryCode\": \"KN\",    \"countryName\": \"Saint Christopher and Nevis\"  },  {    \"countryCode\": \"KP\",    \"countryName\": \"Korea, Democratic People's Republic of\"  },  {    \"countryCode\": \"KR\",    \"countryName\": \"Korea, Republic of\"  },  {    \"countryCode\": \"KV\",    \"countryName\": \"Kosovo\"  },  {    \"countryCode\": \"KW\",    \"countryName\": \"Kuwait\"  },  {    \"countryCode\": \"KY\",    \"countryName\": \"Cayman Islands\"  },  {    \"countryCode\": \"KZ\",    \"countryName\": \"Kazakhstan\"  },  {    \"countryCode\": \"LA\",    \"countryName\": \"Lao People's Democratic Republic\"  },  {    \"countryCode\": \"LB\",    \"countryName\": \"Lebanon\"  },  {    \"countryCode\": \"LC\",    \"countryName\": \"Saint Lucia\"  },  {    \"countryCode\": \"LI\",    \"countryName\": \"Liechtenstein\"  },  {    \"countryCode\": \"LK\",    \"countryName\": \"Sri Lanka\"  },  {    \"countryCode\": \"LR\",    \"countryName\": \"Liberia\"  },  {    \"countryCode\": \"LS\",    \"countryName\": \"Lesotho\"  },  {    \"countryCode\": \"LT\",    \"countryName\": \"Lithuania\"  },  {    \"countryCode\": \"LU\",    \"countryName\": \"Luxembourg\"  },  {    \"countryCode\": \"LV\",    \"countryName\": \"Latvia\"  },  {    \"countryCode\": \"LY\",    \"countryName\": \"Libya\"  },  {    \"countryCode\": \"MA\",    \"countryName\": \"Morocco\"  },  {    \"countryCode\": \"MD\",    \"countryName\": \"Moldova, Republic of\"  },  {    \"countryCode\": \"ME\",    \"countryName\": \"Montenegro\"  },  {    \"countryCode\": \"MG\",    \"countryName\": \"Madagascar\"  },  {    \"countryCode\": \"MK\",    \"countryName\": \"Macedonia, The Former Yugoslav Republic of\"  },  {    \"countryCode\": \"ML\",    \"countryName\": \"Mali\"  },  {    \"countryCode\": \"MM\",    \"countryName\": \"Myanmar\"  },  {    \"countryCode\": \"MN\",    \"countryName\": \"Mongolia\"  },  {    \"countryCode\": \"MO\",    \"countryName\": \"Macao\"  },  {    \"countryCode\": \"MQ\",    \"countryName\": \"Martinique\"  },  {    \"countryCode\": \"MR\",    \"countryName\": \"Mauritania\"  },  {    \"countryCode\": \"MS\",    \"countryName\": \"Montserrat\"  },  {    \"countryCode\": \"MT\",    \"countryName\": \"Malta\"  },  {    \"countryCode\": \"MU\",    \"countryName\": \"Mauritius\"  },  {    \"countryCode\": \"MV\",    \"countryName\": \"Maldives\"  },  {    \"countryCode\": \"MW\",    \"countryName\": \"Malawi\"  },  {    \"countryCode\": \"MX\",    \"countryName\": \"Mexico\"  },  {    \"countryCode\": \"MY\",    \"countryName\": \"Malaysia\"  },  {    \"countryCode\": \"MZ\",    \"countryName\": \"Mozambique\"  },  {    \"countryCode\": \"NA\",    \"countryName\": \"Namibia\"  },  {    \"countryCode\": \"NC\",    \"countryName\": \"New Caledonia\"  },  {    \"countryCode\": \"NE\",    \"countryName\": \"Niger\"  },  {    \"countryCode\": \"NG\",    \"countryName\": \"Nigeria\"  },  {    \"countryCode\": \"NI\",    \"countryName\": \"Nicaragua\"  },  {    \"countryCode\": \"NL\",    \"countryName\": \"Netherlands\"  },  {    \"countryCode\": \"NO\",    \"countryName\": \"Norway\"  },  {    \"countryCode\": \"NP\",    \"countryName\": \"Nepal\"  },  {    \"countryCode\": \"NR\",    \"countryName\": \"Nauru\"  },  {    \"countryCode\": \"NZ\",    \"countryName\": \"New Zealand\"  },  {    \"countryCode\": \"OM\",    \"countryName\": \"Oman\"  },  {    \"countryCode\": \"PA\",    \"countryName\": \"Panama\"  },  {    \"countryCode\": \"PE\",    \"countryName\": \"Peru\"  },  {    \"countryCode\": \"PF\",    \"countryName\": \"French Polynesia\"  },  {    \"countryCode\": \"PG\",    \"countryName\": \"Papua New Guinea\"  },  {    \"countryCode\": \"PH\",    \"countryName\": \"Philippines\"  },  {    \"countryCode\": \"PK\",    \"countryName\": \"Pakistan\"  },  {    \"countryCode\": \"PL\",    \"countryName\": \"Poland\"  },  {    \"countryCode\": \"PM\",    \"countryName\": \"Saint Pierre and Miquelon\"  },  {    \"countryCode\": \"PN\",    \"countryName\": \"Pitcairn Island\"  },  {    \"countryCode\": \"PT\",    \"countryName\": \"Portugal\"  },  {    \"countryCode\": \"PY\",    \"countryName\": \"Paraguay\"  },  {    \"countryCode\": \"QA\",    \"countryName\": \"Qatar\"  },  {    \"countryCode\": \"RE\",    \"countryName\": \"Réunion\"  },  {    \"countryCode\": \"RO\",    \"countryName\": \"Romania\"  },  {    \"countryCode\": \"RS\",    \"countryName\": \"Serbia\"  },  {    \"countryCode\": \"RU\",    \"countryName\": \"Russian Federation\"  },  {    \"countryCode\": \"RW\",    \"countryName\": \"Rwanda\"  },  {    \"countryCode\": \"SA\",    \"countryName\": \"Saudi Arabia\"  },  {    \"countryCode\": \"SB\",    \"countryName\": \"Solomon Islands\"  },  {    \"countryCode\": \"SC\",    \"countryName\": \"Seychelles\"  },  {    \"countryCode\": \"SD\",    \"countryName\": \"Sudan\"  },  {    \"countryCode\": \"SE\",    \"countryName\": \"Sweden\"  },  {    \"countryCode\": \"SG\",    \"countryName\": \"Singapore\"  },  {    \"countryCode\": \"SH\",    \"countryName\": \"Saint Helena\"  },  {    \"countryCode\": \"SI\",    \"countryName\": \"Slovenia\"  },  {    \"countryCode\": \"SK\",    \"countryName\": \"Slovakia\"  },  {    \"countryCode\": \"SL\",    \"countryName\": \"Sierra Leone\"  },  {    \"countryCode\": \"SM\",    \"countryName\": \"San Marino\"  },  {    \"countryCode\": \"SN\",    \"countryName\": \"Senegal\"  },  {    \"countryCode\": \"SR\",    \"countryName\": \"Suriname\"  },  {    \"countryCode\": \"ST\",    \"countryName\": \"Sao Tome and Principe\"  },  {    \"countryCode\": \"SV\",    \"countryName\": \"El Salvador\"  },  {    \"countryCode\": \"SX\",    \"countryName\": \"Sint Maarten \"  },  {    \"countryCode\": \"SY\",    \"countryName\": \"Syrian Arab Republic\"  },  {    \"countryCode\": \"SZ\",    \"countryName\": \"Swaziland\"  },  {    \"countryCode\": \"TA\",    \"countryName\": \"Tristan da Cunha\"  },  {    \"countryCode\": \"TC\",    \"countryName\": \"Turks and Caicos Islands\"  },  {    \"countryCode\": \"TD\",    \"countryName\": \"Chad\"  },  {    \"countryCode\": \"TG\",    \"countryName\": \"Togo\"  },  {    \"countryCode\": \"TH\",    \"countryName\": \"Thailand\"  },  {    \"countryCode\": \"TJ\",    \"countryName\": \"Tajikistan\"  },  {    \"countryCode\": \"TL\",    \"countryName\": \"Timor-Leste\"  },  {    \"countryCode\": \"TM\",    \"countryName\": \"Turkmenistan\"  },  {    \"countryCode\": \"TN\",    \"countryName\": \"Tunisia\"  },  {    \"countryCode\": \"TO\",    \"countryName\": \"Tonga\"  },  {    \"countryCode\": \"TR\",    \"countryName\": \"Turkey\"  },  {    \"countryCode\": \"TT\",    \"countryName\": \"Trinidad and Tobago\"  },  {    \"countryCode\": \"TV\",    \"countryName\": \"Tuvalu\"  },  {    \"countryCode\": \"TW\",    \"countryName\": \"Taiwan, Province of China\"  },  {    \"countryCode\": \"TZ\",    \"countryName\": \"Tanzania, United Republic of\"  },  {    \"countryCode\": \"UA\",    \"countryName\": \"Ukraine\"  },  {    \"countryCode\": \"UG\",    \"countryName\": \"Uganda\"  },  {    \"countryCode\": \"US\",    \"countryName\": \"United States\"  },  {    \"countryCode\": \"UY\",    \"countryName\": \"Uruguay\"  },  {    \"countryCode\": \"UZ\",    \"countryName\": \"Uzbekistan\"  },  {    \"countryCode\": \"VA\",    \"countryName\": \"Holy See (Vatican City State)\"  },  {    \"countryCode\": \"VC\",    \"countryName\": \"Saint Vincent and the Grenadines\"  },  {    \"countryCode\": \"VE\",    \"countryName\": \"Venezuela, Bolivarian Republic of\"  },  {    \"countryCode\": \"VG\",    \"countryName\": \"Virgin Islands, British\"  },  {    \"countryCode\": \"VN\",    \"countryName\": \"Viet Nam\"  },  {    \"countryCode\": \"VU\",    \"countryName\": \"Vanuatu\"  },  {    \"countryCode\": \"WF\",    \"countryName\": \"Wallis and Futuna\"  },  {    \"countryCode\": \"WS\",    \"countryName\": \"Samoa\"  },  {    \"countryCode\": \"YE\",    \"countryName\": \"Yemen\"  },  {    \"countryCode\": \"ZA\",    \"countryName\": \"South Africa\"  },  {    \"countryCode\": \"ZM\",    \"countryName\": \"Zambia\"  },  {    \"countryCode\": \"ZW\",    \"countryName\": \"Zimbabwe\"  }]";

    public static JSONObject getFrom() {
        return from;
    }

    public static void setFrom(JSONObject mfrom) {
        from = mfrom;
    }

    public static JSONObject getTo() {
        return to;
    }

    public static void setTo(JSONObject mto) {
        to = mto;
    }

    public static String getFromCountrytCode() {
        return fromCountryCode;
    }
    public static void setFromCountrytCode(String fromCountryCode) {
        GlobalDataHolder.fromCountryCode = fromCountryCode;
    }

    public static void clearOtherTax() {
        GlobalDataHolder.otherTax = 0;
    }
}
