package com.dodemy.koingettingstarted.utils.extentions

import android.widget.ImageView
import androidx.databinding.BindingAdapter


/**
 *
 *
 * Usage:
 *
 * How to call:
 *
 * Useful parameter:
 *
 */

@BindingAdapter("srcUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (imageUrl == null) return
    com.dodemy.koingettingstarted.utils.imageloader.loadImage(imageUrl, view)
}