package com.example.lithamguzuli.jzcourier;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


import java.lang.*;




public class MapActivity extends AppCompatActivity
        implements  OnMapReadyCallback, //NavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    FragmentManager mFragmentManager;
    LocationManager Location_mananger;
    private Button start_routeB, proceed_routeB;
    private EditText start_location_field,finish_location_field;



    // private static final String SANDBOX_TOKENIZATION_KEY = "sandbox_tmxhyf7d_dcpspy2brwdjr3qn";
    private static final String ORDER_NODE = "Order";
    private static final String SERVE_NODE = "Serving";
    //private static final int DROP_IN_REQUEST_CODE = 567;


    String Start_location;
    private GoogleMap gMap;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    private Marker currentLocationMarker=null;

    private static final long REQUEST_INTERVAL = 1000L;
    private static final float ZOOM_LEVEL = 18f;
    private static final int LOCATION_REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        start_routeB=(Button)findViewById(R.id.route1);
        proceed_routeB=(Button)findViewById(R.id.confirm_Slocation_button);
        start_location_field=(EditText)findViewById(R.id.end_location);
        Start_location=start_location_field.getText().toString();
        start_routeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onsearch();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        proceed_routeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              proceedActivity();
            }
        });
        //search_Field=(EditText)findViewById(R.id.Search_addressField);



        checkLocationPermission();
        //onMapReady(gMap);
        //onRequestPermissionsResult();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Location_mananger=(LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location_mananger.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);
    }

    public void onsearch() throws IOException {

        start_location_field=(EditText)findViewById(R.id.end_location);


        String inital_location=start_location_field.getText().toString();
        List<Address> addressList=null;
        if(!inital_location.equals("") ||inital_location!=null){
            Geocoder geocoder=new Geocoder(this);
            try{
                 addressList=geocoder.getFromLocationName(inital_location,1);
            }catch (IOException e){
                e.printStackTrace();
            }

            Address address= addressList.get(0);
            LatLng latlng=new LatLng(address.getLatitude(),address.getLongitude());
            replaceMarker(latlng);
            //gMap.addMarker(new MarkerOptions().position(latlng).title("marker"));
            gMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));

        }
    }

    public void proceedActivity(){
        if (Start_location.isEmpty() && currentLocationMarker==null){
            Toast.makeText(this,"Verify start location ",Toast.LENGTH_SHORT).show();
        }
        else{

            Intent intent=new Intent(MapActivity.this,Maps2Activity.class);
            startActivity(intent);
        }

    }

    public void replaceMarker(LatLng latLng) {
        // Remove the previous marker

        if (currentLocationMarker != null) {
            currentLocationMarker.remove();

        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("there u are");
        // Add a marker indicating the user's current position to the Google Map
        currentLocationMarker = gMap.addMarker(markerOptions);
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onLocationChanged(Location location) {
        // Store the current location of the user
        gMap.clear();

        lastLocation = location;
        LatLng current_latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (currentLocationMarker == null) {
            // Move the camera to the user's current location on the first location update
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(current_latLng, ZOOM_LEVEL));
        }

        replaceMarker(current_latLng);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            setUpGoogleApiClient();
            // Draw an indication of the user's current location on the map
            gMap.setMyLocationEnabled(true);
        }
    }






    /**
     * Sets up the Google API client to use the location services API and relevant callbacks.
     */
    private synchronized void setUpGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    public void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }else{
            Toast.makeText(this,"No location permission",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    if (googleApiClient == null) {
                        setUpGoogleApiClient();
                    }
                    gMap.setMyLocationEnabled(true);
                }
            } else {
                // Show a message that the position has not been granted
            }
        }

    }

    /**
     * Adds a marker to the current position.
     */


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }





}
