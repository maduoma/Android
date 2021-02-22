/*
 * Copyright (c) 2021. ~ Maduabughichi Achilefu.
 */



package com.dodemy.productswitharchitecturecomponents.utilities

import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.getDeviceWidthInDP(): Int {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return (metrics.widthPixels / metrics.density).toInt()
}

fun List<Any>?.isNullOrEmpty() = this == null || this.isEmpty()