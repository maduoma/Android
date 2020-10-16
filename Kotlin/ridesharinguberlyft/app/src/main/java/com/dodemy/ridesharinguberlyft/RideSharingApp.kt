package com.dodemy.ridesharinguberlyft

import android.app.Application
import com.dodemy.ridesharinguberlyft.R
import com.google.android.libraries.places.api.Places
import com.google.maps.GeoApiContext
import com.dodemy.ridesharinguberlyft.simulator.Simulator

class RideSharingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Places.initialize(applicationContext, getString(R.string.google_maps_key));
        Simulator.geoApiContext = GeoApiContext.Builder()
            .apiKey(getString(R.string.google_maps_key))
            .build()
    }

}