/* Geolocation
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava.Models;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.Serializable;


/**
 * TODO for ProjectPart 5
 *
 * Handles all location based settings that
 * allow the user to properly add a location to their
 * habit event.
 *
 * @version 1.0
 */
public class Geolocation implements Serializable{
    Context context;
    Activity activity;
    Location my_location;
    LocationManager locationManager;
    String provider = LocationManager.GPS_PROVIDER;

    /**
     * Constructor for Geolocation
     *
     * @param context used to get location
     */
    public Geolocation(final Context context, Activity activity) {
        this.context = context;
        this.activity = activity;


        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //TODO: Require permission handling the first instance on a device perhaps?

        //permissions

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
            return;

        } else {
            locationManager.requestLocationUpdates(provider, 60 * 10, 50, new LocationListener() {

                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                public void onProviderEnabled(String s) {

                }

                public void onProviderDisabled(String s) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);

                    context.startActivity(intent);

                }

                public void onLocationChanged(Location location) {
                    my_location = location;
                }
            });

        }
    }

    /**
     * Gets the location.
     *
     * @return location
     */
    public Location getLocation(){
        if(my_location == null){
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!=
                    PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                my_location = locationManager.getLastKnownLocation(provider);
            }
        }
        if(my_location == null){
            my_location = new Location(provider);
            my_location.setLatitude(0);
            my_location.setLongitude(0);
            my_location.setAltitude(0);
        }
        return this.my_location;
    }

    /**
     * Sets the location.
     *
     * @param location instance of Location
     */
    /*public void setLocation(Location location){
        this. = location;
    }*/
}
