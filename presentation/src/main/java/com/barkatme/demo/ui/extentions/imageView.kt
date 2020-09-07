// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun ImageView.loadGif(url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .transition(withCrossFade())
        .into(this)
}