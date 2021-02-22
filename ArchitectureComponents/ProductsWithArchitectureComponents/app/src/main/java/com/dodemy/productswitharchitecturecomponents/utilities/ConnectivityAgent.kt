/*
 * Copyright (c) 2021 Maduabughichi Achilefu.
 */

package com.dodemy.productswitharchitecturecomponents.utilities

import android.content.Context
import android.net.ConnectivityManager


class ConnectivityAgent(private val context: Context) {

    fun isDeviceConnectedToInternet(): Boolean {
        val service = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = service.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}