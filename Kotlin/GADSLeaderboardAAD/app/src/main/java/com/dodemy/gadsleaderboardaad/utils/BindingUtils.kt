package com.dodemy.gadsleaderboardaad.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dodemy.gadsleaderboardaad.R


@BindingAdapter("imageUrl")
fun ImageView.loadUrl(imageUrl: String) {
    Glide.with(context).load(imageUrl).centerCrop().placeholder(R.drawable.skilltrimmed).into(this)
}

@BindingAdapter("timeUrl")
fun ImageView.loadHourUrl(imageUrl: String) {
    Glide.with(context).load(imageUrl).centerCrop().placeholder(R.drawable.toplearner).into(this)
}
