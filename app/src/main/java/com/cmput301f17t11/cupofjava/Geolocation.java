/* Geolocation
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/**
 * TODO for ProjectPart 5
 *
 * Handles all location based settings that
 * allow the user to properly add a location to their
 * habit event.
 *
 * @version 1.0
 */
public class Geolocation {
    Context context;
    Location location;
    LocationManager locationManager;
    String provider = LocationManager.GPS_PROVIDER;

    /**
     * Constructor for Geolocation
     *
     * @param context used to get location
     */
    public Geolocation(Context context){
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //TODO: Require permission handling the first instance on a device perhaps?
    }

    /**
     * Gets the location.
     *
     * @return location
     */
    public Location getLocation(){
        return this.location;
    }

    /**
     * Sets the location.
     *
     * @param location instance of Location
     */
    public void setLocation(Location location){
        this.location = location;
    }
}
