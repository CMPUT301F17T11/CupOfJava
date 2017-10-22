package com.cmput301f17t11.cupofjava;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/**
 *
 * This class is to be completed for project part 5
 */

public class Geolocation {
    Context context;
    Location location;
    LocationManager locationManager;
    String provider = LocationManager.GPS_PROVIDER;

    public Geolocation(Context context){
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //TODO: Require permission handling the first instance on a device perhaps?
    }

    public Location getLocation(){
        return this.location;
    }

    public void setLocation(Location location){
        this.location = location;
    }
}
