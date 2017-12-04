package com.cmput301f17t11.cupofjava.Views;

import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cmput301f17t11.cupofjava.Models.Geolocation;
import com.cmput301f17t11.cupofjava.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;

    double currentLat;
    double currentLon;
    double [] latitudes;
    double [] longitudes;
    Geolocation location;
    int type;
    EditText showLocation;
    Button getLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (googleServicesAvailable()) {
            Toast.makeText(this, "Google Play Services Works", Toast.LENGTH_LONG).show();
            setContentView(R.layout.maps_activity);
            initMap();
        }


        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){
            latitudes = bundle.getDoubleArray("lat");
            longitudes = bundle.getDoubleArray("lon");
            type = bundle.getInt("type");

            currentLat = bundle.getDouble("currentLat");
            currentLon = bundle.getDouble("currentLon");

        }
        if(type == 1){  //within 5km

            for (int i = 0; i < latitudes.length; i++)
            {
                double lat2= latitudes[i];
                double lng2= longitudes[i];
                if ((within5(currentLat, currentLon, lat2, lng2) <= 5.0) == false){
                    //do nothing
                }
                else{
                    latitudes[i] = 0.0;
                    longitudes[i] = 0.0;
                }
            }

        }

    }

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        for(int i = 0; i < (latitudes).length; i++){
            if(latitudes[i]!= 0.0 && longitudes[i]!= 0.0 ) {
                goToLocationZoomed(latitudes[i], longitudes[i], 15);
            }

        }
    }

    private void goToLocationZoomed(double lat, double lng, float zoom) {
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mGoogleMap.moveCamera(cameraUpdate);
        MarkerOptions markerOptions = new MarkerOptions()
                .title("U of A").position(new LatLng(lat, lng));
        mGoogleMap.addMarker(markerOptions);
        /*for(int i = 0; i < eventLoc.size(); i ++){
            double latitude = eventLoc.get(i).getLatitude();
            double longitude = eventLoc.get(i).getLongitude();
            MarkerOptions markerOptions = new MarkerOptions()
                    .title("U of A").position(new LatLng(latitude, longitude));
            mGoogleMap.addMarker(markerOptions);
        }*/

    }

    /**
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return dist distance in km within the two points
     */
    public static double within5(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371.0; // km (or 6371.0 kilometers)
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        return dist;
    }


    }
