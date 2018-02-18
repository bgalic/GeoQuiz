package com.example.geoquiz;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private JSONObject countryCodesJson;
    Button btnToScores;
    private GoogleMap mMap;
    private static final int REQUEST_CODE = 1;
    private String[] myPermissions = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    SupportMapFragment mapFragment;
    Button btnLocation;
    EditText etLocation;
    LinearLayout mapsLoader;
    LinearLayout mapWrapper;
    LinearLayout locationFoundWrapper;
    TextView tvFoundLocation;
    DBHelper dbHelper;
    String place;
    String continent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        String countryCodes = "{" +
                "\"AF\":\"Asia\", // \"Islamic Republic of Afghanistan\") \n" +
                "\"AX\":\"Europe\", // \"Åland Islands\") \n" +
                "\"AL\":\"Europe\", // \"Republic of Albania\") \n" +
                "\"DZ\":\"Africa\", // \"People's Democratic Republic of Algeria\") \n" +
                "\"AS\":\"Oceania\", // \"American Samoa\") \n" +
                "\"AD\":\"Europe\", // \"Principality of Andorra\") \n" +
                "\"AO\":\"Africa\", // \"Republic of Angola\") \n" +
                "\"AI\":\"North America\", // \"Anguilla\") \n" +
                "\"AQ\":\"Antarctica\", // \"Antarctica (the territory South of 60 deg S)\") \n" +
                "\"AG\":\"North America\", // \"Antigua and Barbuda\") \n" +
                "\"AR\":\"South America\", // \"Argentine Republic\") \n" +
                "\"AM\":\"Asia\", // \"Republic of Armenia\") \n" +
                "\"AW\":\"North America\", // \"Aruba\") \n" +
                "\"AU\":\"Oceania\", // \"Commonwealth of Australia\") \n" +
                "\"AT\":\"Europe\", // \"Republic of Austria\") \n" +
                "\"AZ\":\"Asia\", // \"Republic of Azerbaijan\") \n" +
                "\"BS\":\"North America\", // \"Commonwealth of the Bahamas\") \n" +
                "\"BH\":\"Asia\", // \"Kingdom of Bahrain\") \n" +
                "\"BD\":\"Asia\", // \"People's Republic of Bangladesh\") \n" +
                "\"BB\":\"North America\", // \"Barbados\") \n" +
                "\"BY\":\"Europe\", // \"Republic of Belarus\") \n" +
                "\"BE\":\"Europe\", // \"Kingdom of Belgium\") \n" +
                "\"BZ\":\"North America\", // \"Belize\") \n" +
                "\"BJ\":\"Africa\", // \"Republic of Benin\") \n" +
                "\"BM\":\"North America\", // \"Bermuda\") \n" +
                "\"BT\":\"Asia\", // \"Kingdom of Bhutan\") \n" +
                "\"BO\":\"South America\", // \"Plurinational State of Bolivia\") \n" +
                "\"BQ\":\"North America\", // '535' \n" +
                "\"BA\":\"Europe\", // \"Bosnia and Herzegovina\") \n" +
                "\"BW\":\"Africa\", // \"Republic of Botswana\") \n" +
                "\"BV\":\"Antarctica\", // \"Bouvet Island (Bouvetoya)\") \n" +
                "\"BR\":\"South America\", // \"Federative Republic of Brazil\") \n" +
                "\"IO\":\"Asia\", // \"British Indian Ocean Territory (Chagos Archipelago)\") \n" +
                "\"VG\":\"North America\", // \"British Virgin Islands\") \n" +
                "\"BN\":\"Asia\", // \"Brunei Darussalam\") \n" +
                "\"BG\":\"Europe\", // \"Republic of Bulgaria\") \n" +
                "\"BF\":\"Africa\", // \"Burkina Faso\") \n" +
                "\"BI\":\"Africa\", // \"Republic of Burundi\") \n" +
                "\"KH\":\"Asia\", // \"Kingdom of Cambodia\") \n" +
                "\"CM\":\"Africa\", // \"Republic of Cameroon\") \n" +
                "\"CA\":\"North America\", // \"Canada\") \n" +
                "\"CV\":\"Africa\", // \"Republic of Cape Verde\") \n" +
                "\"KY\":\"North America\", // \"Cayman Islands\") \n" +
                "\"CF\":\"Africa\", // \"Central African Republic\") \n" +
                "\"TD\":\"Africa\", // \"Republic of Chad\") \n" +
                "\"CL\":\"South America\", // \"Republic of Chile\") \n" +
                "\"CN\":\"Asia\", // \"People's Republic of China\") \n" +
                "\"CX\":\"Asia\", // \"Christmas Island\") \n" +
                "\"CC\":\"Asia\", // \"Cocos (Keeling) Islands\") \n" +
                "\"CO\":\"South America\", // \"Republic of Colombia\") \n" +
                "\"KM\":\"Africa\", // \"Union of the Comoros\") \n" +
                "\"CD\":\"Africa\", // \"Democratic Republic of the Congo\") \n" +
                "\"CG\":\"Africa\", // \"Republic of the Congo\") \n" +
                "\"CK\":\"Oceania\", // \"Cook Islands\") \n" +
                "\"CR\":\"North America\", // \"Republic of Costa Rica\") \n" +
                "\"CI\":\"Africa\", // \"Republic of Cote d'Ivoire\") \n" +
                "\"HR\":\"Europe\", // \"Republic of Croatia\") \n" +
                "\"CU\":\"North America\", // \"Republic of Cuba\") \n" +
                "\"CW\":\"North America\", // \"Curaçao\") \n" +
                "\"CY\":\"Asia\", // \"Republic of Cyprus\") \n" +
                "\"CZ\":\"Europe\", // \"Czech Republic\") \n" +
                "\"DK\":\"Europe\", // \"Kingdom of Denmark\") \n" +
                "\"DJ\":\"Africa\", // \"Republic of Djibouti\") \n" +
                "\"DM\":\"North America\", // \"Commonwealth of Dominica\") \n" +
                "\"DO\":\"North America\", // \"Dominican Republic\") \n" +
                "\"EC\":\"South America\", // \"Republic of Ecuador\") \n" +
                "\"EG\":\"Africa\", // \"Arab Republic of Egypt\") \n" +
                "\"SV\":\"North America\", // \"Republic of El Salvador\") \n" +
                "\"GQ\":\"Africa\", // \"Republic of Equatorial Guinea\") \n" +
                "\"ER\":\"Africa\", // \"State of Eritrea\") \n" +
                "\"EE\":\"Europe\", // \"Republic of Estonia\") \n" +
                "\"ET\":\"Africa\", // \"Federal Democratic Republic of Ethiopia\") \n" +
                "\"FO\":\"Europe\", // \"Faroe Islands\") \n" +
                "\"FK\":\"South America\", // \"Falkland Islands (Malvinas)\") \n" +
                "\"FJ\":\"Oceania\", // \"Republic of Fiji\") \n" +
                "\"FI\":\"Europe\", // \"Republic of Finland\") \n" +
                "\"FR\":\"Europe\", // \"French Republic\") \n" +
                "\"GF\":\"South America\", // \"French Guiana\") \n" +
                "\"PF\":\"Oceania\", // \"French Polynesia\") \n" +
                "\"TF\":\"Antarctica\", // \"French Southern Territories\") \n" +
                "\"GA\":\"Africa\", // \"Gabonese Republic\") \n" +
                "\"GM\":\"Africa\", // \"Republic of the Gambia\") \n" +
                "\"GE\":\"Asia\", // \"Georgia\") \n" +
                "\"DE\":\"Europe\", // \"Federal Republic of Germany\") \n" +
                "\"GH\":\"Africa\", // \"Republic of Ghana\") \n" +
                "\"GI\":\"Europe\", // \"Gibraltar\") \n" +
                "\"GR\":\"Europe\", // \"Hellenic Republic Greece\") \n" +
                "\"GL\":\"North America\", // \"Greenland\") \n" +
                "\"GD\":\"North America\", // \"Grenada\") \n" +
                "\"GP\":\"North America\", // \"Guadeloupe\") \n" +
                "\"GU\":\"Oceania\", // \"Guam\") \n" +
                "\"GT\":\"North America\", // \"Republic of Guatemala\") \n" +
                "\"GG\":\"Europe\", // \"Bailiwick of Guernsey\") \n" +
                "\"GN\":\"Africa\", // \"Republic of Guinea\") \n" +
                "\"GW\":\"Africa\", // \"Republic of Guinea-Bissau\") \n" +
                "\"GY\":\"South America\", // \"Co-operative Republic of Guyana\") \n" +
                "\"HT\":\"North America\", // \"Republic of Haiti\") \n" +
                "\"HM\":\"Antarctica\", // \"Heard Island and McDonald Islands\") \n" +
                "\"VA\":\"Europe\", // \"Holy See (Vatican City State)\") \n" +
                "\"HN\":\"North America\", // \"Republic of Honduras\") \n" +
                "\"HK\":\"Asia\", // \"Hong Kong Special Administrative Region of China\") \n" +
                "\"HU\":\"Europe\", // \"Hungary\") \n" +
                "\"IS\":\"Europe\", // \"Republic of Iceland\") \n" +
                "\"IN\":\"Asia\", // \"Republic of India\") \n" +
                "\"ID\":\"Asia\", // \"Republic of Indonesia\") \n" +
                "\"IR\":\"Asia\", // \"Islamic Republic of Iran\") \n" +
                "\"IQ\":\"Asia\", // \"Republic of Iraq\") \n" +
                "\"IE\":\"Europe\", // \"Ireland\") \n" +
                "\"IM\":\"Europe\", // \"Isle of Man\") \n" +
                "\"IL\":\"Asia\", // \"State of Israel\") \n" +
                "\"IT\":\"Europe\", // \"Italian Republic\") \n" +
                "\"JM\":\"North America\", // \"Jamaica\") \n" +
                "\"JP\":\"Asia\", // \"Japan\") \n" +
                "\"JE\":\"Europe\", // \"Bailiwick of Jersey\") \n" +
                "\"JO\":\"Asia\", // \"Hashemite Kingdom of Jordan\") \n" +
                "\"KZ\":\"Asia\", // \"Republic of Kazakhstan\") \n" +
                "\"KE\":\"Africa\", // \"Republic of Kenya\") \n" +
                "\"KI\":\"Oceania\", // \"Republic of Kiribati\") \n" +
                "\"KP\":\"Asia\", // \"Democratic People's Republic of Korea\") \n" +
                "\"KR\":\"Asia\", // \"Republic of Korea\") \n" +
                "\"KW\":\"Asia\", // \"State of Kuwait\") \n" +
                "\"KG\":\"Asia\", // \"Kyrgyz Republic\") \n" +
                "\"LA\":\"Asia\", // \"Lao People's Democratic Republic\") \n" +
                "\"LV\":\"Europe\", // \"Republic of Latvia\") \n" +
                "\"LB\":\"Asia\", // \"Lebanese Republic\") \n" +
                "\"LS\":\"Africa\", // \"Kingdom of Lesotho\") \n" +
                "\"LR\":\"Africa\", // \"Republic of Liberia\") \n" +
                "\"LY\":\"Africa\", // \"Libya\") \n" +
                "\"LI\":\"Europe\", // \"Principality of Liechtenstein\") \n" +
                "\"LT\":\"Europe\", // \"Republic of Lithuania\") \n" +
                "\"LU\":\"Europe\", // \"Grand Duchy of Luxembourg\") \n" +
                "\"MO\":\"Asia\", // \"Macao Special Administrative Region of China\") \n" +
                "\"MK\":\"Europe\", // \"Republic of Macedonia\") \n" +
                "\"MG\":\"Africa\", // \"Republic of Madagascar\") \n" +
                "\"MW\":\"Africa\", // \"Republic of Malawi\") \n" +
                "\"MY\":\"Asia\", // \"Malaysia\") \n" +
                "\"MV\":\"Asia\", // \"Republic of Maldives\") \n" +
                "\"ML\":\"Africa\", // \"Republic of Mali\") \n" +
                "\"MT\":\"Europe\", // \"Republic of Malta\") \n" +
                "\"MH\":\"Oceania\", // \"Republic of the Marshall Islands\") \n" +
                "\"MQ\":\"North America\", // \"Martinique\") \n" +
                "\"MR\":\"Africa\", // \"Islamic Republic of Mauritania\") \n" +
                "\"MU\":\"Africa\", // \"Republic of Mauritius\") \n" +
                "\"YT\":\"Africa\", // \"Mayotte\") \n" +
                "\"MX\":\"North America\", // \"United Mexican States\") \n" +
                "\"FM\":\"Oceania\", // \"Federated States of Micronesia\") \n" +
                "\"MD\":\"Europe\", // \"Republic of Moldova\") \n" +
                "\"MC\":\"Europe\", // \"Principality of Monaco\") \n" +
                "\"MN\":\"Asia\", // \"Mongolia\") \n" +
                "\"ME\":\"Europe\", // \"Montenegro\") \n" +
                "\"MS\":\"North America\", // \"Montserrat\") \n" +
                "\"MA\":\"Africa\", // \"Kingdom of Morocco\") \n" +
                "\"MZ\":\"Africa\", // \"Republic of Mozambique\") \n" +
                "\"MM\":\"Asia\", // \"Republic of the Union of Myanmar\") \n" +
                "\"NA\":\"Africa\", // \"Republic of Namibia\") \n" +
                "\"NR\":\"Oceania\", // \"Republic of Nauru\") \n" +
                "\"NP\":\"Asia\", // \"Federal Democratic Republic of Nepal\") \n" +
                "\"NL\":\"Europe\", // \"Kingdom of the Netherlands\") \n" +
                "\"NC\":\"Oceania\", // \"New Caledonia\") \n" +
                "\"NZ\":\"Oceania\", // \"New Zealand\") \n" +
                "\"NI\":\"North America\", // \"Republic of Nicaragua\") \n" +
                "\"NE\":\"Africa\", // \"Republic of Niger\") \n" +
                "\"NG\":\"Africa\", // \"Federal Republic of Nigeria\") \n" +
                "\"NU\":\"Oceania\", // \"Niue\") \n" +
                "\"NF\":\"Oceania\", // \"Norfolk Island\") \n" +
                "\"MP\":\"Oceania\", // \"Commonwealth of the Northern Mariana Islands\") \n" +
                "\"NO\":\"Europe\", // \"Kingdom of Norway\") \n" +
                "\"OM\":\"Asia\", // \"Sultanate of Oman\") \n" +
                "\"PK\":\"Asia\", // \"Islamic Republic of Pakistan\") \n" +
                "\"PW\":\"Oceania\", // \"Republic of Palau\") \n" +
                "\"PS\":\"Asia\", // \"Occupied Palestinian Territory\") \n" +
                "\"PA\":\"North America\", // \"Republic of Panama\") \n" +
                "\"PG\":\"Oceania\", // \"Independent State of Papua New Guinea\") \n" +
                "\"PY\":\"South America\", // \"Republic of Paraguay\") \n" +
                "\"PE\":\"South America\", // \"Republic of Peru\") \n" +
                "\"PH\":\"Asia\", // \"Republic of the Philippines\") \n" +
                "\"PN\":\"Oceania\", // \"Pitcairn Islands\") \n" +
                "\"PL\":\"Europe\", // \"Republic of Poland\") \n" +
                "\"PT\":\"Europe\", // \"Portuguese Republic\") \n" +
                "\"PR\":\"North America\", // \"Commonwealth of Puerto Rico\") \n" +
                "\"QA\":\"Asia\", // \"State of Qatar\") \n" +
                "\"RE\":\"Africa\", // \"Réunion\") \n" +
                "\"RO\":\"Europe\", // \"Romania\") \n" +
                "\"RU\":\"Europe\", // \"Russian Federation\") \n" +
                "\"RW\":\"Africa\", // \"Republic of Rwanda\") \n" +
                "\"BL\":\"North America\", // \"Saint Barthélemy\") \n" +
                "\"SH\":\"Africa\", // '654' \n" +
                "\"KN\":\"North America\", // \"Federation of Saint Kitts and Nevis\") \n" +
                "\"LC\":\"North America\", // \"Saint Lucia\") \n" +
                "\"MF\":\"North America\", // \"Saint Martin (French part)\") \n" +
                "\"PM\":\"North America\", // \"Saint Pierre and Miquelon\") \n" +
                "\"VC\":\"North America\", // \"Saint Vincent and the Grenadines\") \n" +
                "\"WS\":\"Oceania\", // \"Independent State of Samoa\") \n" +
                "\"SM\":\"Europe\", // \"Republic of San Marino\") \n" +
                "\"ST\":\"Africa\", // \"Democratic Republic of Sao Tome and Principe\") \n" +
                "\"SA\":\"Asia\", // \"Kingdom of Saudi Arabia\") \n" +
                "\"SN\":\"Africa\", // \"Republic of Senegal\") \n" +
                "\"RS\":\"Europe\", // \"Republic of Serbia\") \n" +
                "\"SC\":\"Africa\", // \"Republic of Seychelles\") \n" +
                "\"SL\":\"Africa\", // \"Republic of Sierra Leone\") \n" +
                "\"SG\":\"Asia\", // \"Republic of Singapore\") \n" +
                "\"SX\":\"North America\", // \"Sint Maarten (Dutch part)\") \n" +
                "\"SK\":\"Europe\", // \"Slovakia (Slovak Republic)\") \n" +
                "\"SI\":\"Europe\", // \"Republic of Slovenia\") \n" +
                "\"SB\":\"Oceania\", // \"Solomon Islands\") \n" +
                "\"SO\":\"Africa\", // \"Somali Republic\") \n" +
                "\"ZA\":\"Africa\", // \"Republic of South Africa\") \n" +
                "\"GS\":\"Antarctica\", // \"South Georgia and the South Sandwich Islands\") \n" +
                "\"SS\":\"Africa\", // \"Republic of South Sudan\") \n" +
                "\"ES\":\"Europe\", // \"Kingdom of Spain\") \n" +
                "\"LK\":\"Asia\", // \"Democratic Socialist Republic of Sri Lanka\") \n" +
                "\"SD\":\"Africa\", // \"Republic of Sudan\") \n" +
                "\"SR\":\"South America\", // \"Republic of Suriname\") \n" +
                "\"SJ\":\"Europe\", // \"Svalbard & Jan Mayen Islands\") \n" +
                "\"SZ\":\"Africa\", // \"Kingdom of Swaziland\") \n" +
                "\"SE\":\"Europe\", // \"Kingdom of Sweden\") \n" +
                "\"CH\":\"Europe\", // \"Swiss Confederation\") \n" +
                "\"SY\":\"Asia\", // \"Syrian Arab Republic\") \n" +
                "\"TW\":\"Asia\", // \"Taiwan \n" +
                "\"TJ\":\"Asia\", // \"Republic of Tajikistan\") \n" +
                "\"TZ\":\"Africa\", // \"United Republic of Tanzania\") \n" +
                "\"TH\":\"Asia\", // \"Kingdom of Thailand\") \n" +
                "\"TL\":\"Asia\", // \"Democratic Republic of Timor-Leste\") \n" +
                "\"TG\":\"Africa\", // \"Togolese Republic\") \n" +
                "\"TK\":\"Oceania\", // \"Tokelau\") \n" +
                "\"TO\":\"Oceania\", // \"Kingdom of Tonga\") \n" +
                "\"TT\":\"North America\", // \"Republic of Trinidad and Tobago\") \n" +
                "\"TN\":\"Africa\", // \"Tunisian Republic\") \n" +
                "\"TR\":\"Asia\", // \"Republic of Turkey\") \n" +
                "\"TM\":\"Asia\", // \"Turkmenistan\") \n" +
                "\"TC\":\"North America\", // \"Turks and Caicos Islands\") \n" +
                "\"TV\":\"Oceania\", // \"Tuvalu\") \n" +
                "\"UG\":\"Africa\", // \"Republic of Uganda\") \n" +
                "\"UA\":\"Europe\", // \"Ukraine\") \n" +
                "\"AE\":\"Asia\", // \"United Arab Emirates\") \n" +
                "\"GB\":\"Europe\", // \"United Kingdom of Great Britain & Northern Ireland\") \n" +
                "\"US\":\"North America\", // \"United States of America\") \n" +
                "\"UM\":\"Oceania\", // \"United States Minor Outlying Islands\") \n" +
                "\"VI\":\"North America\", // \"United States Virgin Islands\") \n" +
                "\"UY\":\"South America\", // \"Eastern Republic of Uruguay\") \n" +
                "\"UZ\":\"Asia\", // \"Republic of Uzbekistan\") \n" +
                "\"VU\":\"Oceania\", // \"Republic of Vanuatu\") \n" +
                "\"VE\":\"South America\", // \"Bolivarian Republic of Venezuela\") \n" +
                "\"VN\":\"Asia\", // \"Socialist Republic of Vietnam\") \n" +
                "\"WF\":\"Oceania\", // \"Wallis and Futuna\") \n" +
                "\"EH\":\"Africa\", // \"Western Sahara\") \n" +
                "\"YE\":\"Asia\", // \"Yemen\") \n" +
                "\"ZM\":\"Africa\", // \"Republic of Zambia\") \n" +
                "\"ZW\":\"Africa\" // \"Republic of Zimbabwe\"); \n" +
                "}";

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        InitializeUI();
        try {

            countryCodesJson = new JSONObject(countryCodes);

            Log.d("countryCodesJson", countryCodesJson.toString());

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON");
        }

        dbHelper = new DBHelper(this);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (isDeviceOnline(getApplicationContext()))
                        onSearch(v);
                    else
                        Toast.makeText(getApplicationContext(), "Potreban pristup internetu.", Toast.LENGTH_SHORT).show();

            }
        });

        btnToScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this,ContinentsList.class));
            }
        });



    }
    private void InitializeUI() {
        btnLocation = (Button) findViewById(R.id.btnLocation);
        etLocation = (EditText) findViewById(R.id.etLocation);
        mapsLoader = (LinearLayout) findViewById(R.id.mapsLoader);
        mapWrapper = (LinearLayout) findViewById(R.id.mapWrapper);
        locationFoundWrapper = (LinearLayout) findViewById(R.id.locationFoundWrapper);
        tvFoundLocation = (TextView) findViewById(R.id.tvFoundLocation);
        btnToScores = (Button) findViewById(R.id.btnToScores);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(!isDeviceOnline(this)) {
            Toast.makeText(this, "Potrebno se spojiti na Internet.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.e(this.getClass().getSimpleName(), "Permission failed");
            ActivityCompat.requestPermissions(this, myPermissions, REQUEST_CODE);

            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                boolean a = isLocationEnabled(getApplicationContext());
                if(!isLocationEnabled(getApplicationContext())){
                    Toast.makeText(getApplicationContext(),"Uključite lokaciju.",Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                findByClick(latLng);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:


                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    mapFragment.getMapAsync(this);
                } else {
                    Toast.makeText(this,"Potrebno omogućiti lociranje.", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    public void onSearch(View view) {
        mapsLoader.setVisibility(View.VISIBLE);
        mapWrapper.setVisibility(View.GONE);
        String location = etLocation.getText().toString();
        List<Address> addressList = null;
        try {

            if (location != null || !location.equals("")) {
                Geocoder geocoder = new Geocoder(this);
                try {
                    addressList = geocoder.getFromLocationName(location, 1);
                    if (!addressList.isEmpty()){
                        mMap.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mMap.clear();
                android.location.Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Lokacija pretrazivanja"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                if(address != null)
                    showAddressDialog(address);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Neispravna lokacija", Toast.LENGTH_SHORT).show();
        }
        finally {
            mapsLoader.setVisibility(View.GONE);
            mapWrapper.setVisibility(View.VISIBLE);
        }
    }

    public static boolean isDeviceOnline(Context context) {
        boolean isConnectionAvail = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if(netInfo != null)
                return netInfo.isConnected();
            else
                return isConnectionAvail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnectionAvail;
    }

    private void showAddressDialog(final android.location.Address foundAddress){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message;
        if(foundAddress.getLocality() != null){
            message = foundAddress.getLocality() + ", " + foundAddress.getAdminArea() + ", "
                    + foundAddress.getCountryName();
        }
        else {
            message =  foundAddress.getAdminArea() + ", "
                    + foundAddress.getCountryName();
        }
        place = message;
        Iterator<String> iter = countryCodesJson.keys();
        while (iter.hasNext()) {
            String key = iter.next();
            try {
                if(key.equals(foundAddress.getCountryCode())) {
                    Object value = countryCodesJson.get(key);
                    continent = value.toString();
                }

            } catch (JSONException e) {
                Log.e("getCountryValueEx", e.getMessage());
            }
        }
        builder.setTitle("Pronađena lokacija.");


        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Traži pitanja.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        findClosestQuestions(foundAddress);
                    }
                })
                .setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    private void findClosestQuestions(android.location.Address foundAddress){

        LatLng mainPoint = new LatLng(foundAddress.getLatitude(), foundAddress.getLongitude());
        ArrayList<Question> closestQuestions = findClosestQuestions(mainPoint);
        Intent intent = new Intent(MapsActivity.this, QuizActivity.class);
        intent.putExtra("QUESTIONS", closestQuestions);
        intent.putExtra("PLACE", place);
        intent.putExtra("CONTINENT", continent);
        startActivity(intent);
        finish();
    }

    private ArrayList<Question> findClosestQuestions(LatLng mainPoint){
        ArrayList<Place> placesFromDb = dbHelper.getPlaces();
        ArrayList<Question> closestQuestions = new  ArrayList<Question>();
        float smallestDistance = Float.MAX_VALUE;
        float mainPointAndPlacePointDistance;
        int placeIdToGetQuestions = 0;
        String placeValue = "";
        Place itemToRemove = new Place();
        Location mainLocation = new Location("Main point");
        mainLocation.setLatitude(mainPoint.latitude);
        mainLocation.setLongitude(mainPoint.longitude);

        while (closestQuestions.size() < 5) {
            for (Place place :
                    placesFromDb) {

                Location searchLocation = new Location("Point B");
                searchLocation.setLatitude(Double.parseDouble(place.getLatitude()));
                searchLocation.setLongitude(Double.parseDouble(place.getLongitude()));

                mainPointAndPlacePointDistance = mainLocation.distanceTo(searchLocation);
                if (mainPointAndPlacePointDistance < smallestDistance) {
                    smallestDistance = mainPointAndPlacePointDistance;
                    placeIdToGetQuestions = place.getId();
                    placeValue = place.getTemp();
                    itemToRemove = place;
                }
            }
            placesFromDb.remove(itemToRemove);
            ArrayList<Question> currentQuestions = dbHelper.getQuestionsByPlaceId(placeIdToGetQuestions);
            for (Question question:
                    currentQuestions) {
                question.setPlace(placeValue);
                closestQuestions.add(question);
            }
            smallestDistance = Float.MAX_VALUE;
        }

        if(closestQuestions.size() > 5){
            ArrayList<Question> temp = new ArrayList<>(closestQuestions.subList(0,5));
            closestQuestions = temp;
        }
        Log.d("CLOSEST",closestQuestions.toString());
        return closestQuestions;
    }

    private void findByClick(LatLng latLng){
        mapsLoader.setVisibility(View.VISIBLE);
        mapWrapper.setVisibility(View.GONE);
        Address address = null;
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());
            mMap.clear();
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            address = addresses.get(0);
            mMap.addMarker(new MarkerOptions().position(latLng).title("Kliknuta lokacija"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

            if(address != null)
                showAddressDialog(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            mapsLoader.setVisibility(View.GONE);
            mapWrapper.setVisibility(View.VISIBLE);
        }
    }
}
