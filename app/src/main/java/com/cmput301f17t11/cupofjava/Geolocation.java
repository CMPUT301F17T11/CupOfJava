package com.cmput301f17t11.cupofjava;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by nazim on 22/10/17.
 */

public class Geolocation {
    Context context;
    Location location;
    LocationManager locationManager;
    String provider = LocationManager.GPS_PROVIDER;

    public Geolocation(Context context){
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //TODO: Require permission handling the first instance on a device
    }

    public Location getLocation(){
        return this.location;
    }

    public void setLocation(Location location){
        this.location = location;
    }
}
