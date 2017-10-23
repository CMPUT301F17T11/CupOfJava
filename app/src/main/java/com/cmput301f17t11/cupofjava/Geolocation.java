package com.cmput301f17t11.cupofjava;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/**
 * This class is to be completed for project part 5
 *
 * This class handles all location based settings that
 * allow the user to properly add a location to their
 * habit event.
 */

public class Geolocation {
    Context context;
    Location location;
    LocationManager locationManager;
    String provider = LocationManager.GPS_PROVIDER;

    /**
     * Constructor for Geolocation
     * @param context
     */
    public Geolocation(Context context){
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //TODO: Require permission handling the first instance on a device perhaps?
    }

    /**
     * @return
     */
    public Location getLocation(){
        return this.location;
    }

    /**
     * @param location
     */
    public void setLocation(Location location){
        this.location = location;
    }
}
