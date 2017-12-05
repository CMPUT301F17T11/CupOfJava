package com.cmput301f17t11.cupofjava.Views;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;

    double currentLat = 53.525049;
    double currentLon = -113.524605;
    //double [] latitudes;
    //double [] longitudes;
    ArrayList<Double> latitudes;
    ArrayList<Double> longitudes;
    ArrayList<String> markerTitles;
    Geolocation location;
    int type;
    MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (googleServicesAvailable()) {
            Toast.makeText(this, "Google Play Services Works", Toast.LENGTH_LONG).show();
            setContentView(R.layout.maps_activity);
            initMap();
            Intent intent = getIntent();
            latitudes = (ArrayList<Double>) intent.getSerializableExtra("lat");
            longitudes = (ArrayList<Double>) intent.getSerializableExtra("lon");
            markerTitles = intent.getStringArrayListExtra("markerTitles");
            type = intent.getIntExtra("type", type);
            if (type == 1) {  //within 5km

                for (int i = 0; i < latitudes.size(); i++) {
                    double lat2 = latitudes.get(i);
                    double lng2 = longitudes.get(i);
                    if ((within5(currentLat, currentLon, lat2, lng2) <= 5.0) == false) {
                        //do nothing
                    } else {
                        latitudes.remove(i);
                        longitudes.remove(i);
                        markerTitles.remove(i);

                    }
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
        if(mapFragment!= null)
        {
            getFragmentManager().beginTransaction().remove(mapFragment).commit();
        }
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        for(int i = 0; i < latitudes.size(); i++){
                goToLocationZoomed(latitudes.get(i), longitudes.get(i), 10, markerTitles.get(i));


        }
    }


    private void goToLocationZoomed(double lat, double lng, float zoom, String markerTitle) {
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mGoogleMap.moveCamera(cameraUpdate);
        MarkerOptions markerOptions = new MarkerOptions()
                .title(markerTitle).position(new LatLng(lat, lng));
        mGoogleMap.addMarker(markerOptions);


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
