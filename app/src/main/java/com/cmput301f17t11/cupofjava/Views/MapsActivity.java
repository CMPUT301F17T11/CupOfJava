package com.cmput301f17t11.cupofjava.Views;

import android.app.Dialog;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
    ArrayList<Location> eventLoc = new ArrayList<>();
    Location loc;

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
            //eventLoc = bundle.getParcelableArrayList("eventLoc");
            loc = bundle.getParcelable("eventLoc");

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
        double lat = loc.getLatitude();
        double lon = loc.getLongitude();
        goToLocationZoomed(lat,lon, 15);
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
}
