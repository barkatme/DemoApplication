// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun ImageView.loadGif(url: String, previewUrl: String? = null) {
    Glide.with(context)
        .load(url)
        .thumbnail(
            Glide.with(context)
                .load(previewUrl)
                .centerCrop()
                .transition(withCrossFade())
        )
        .centerCrop()
        .transition(withCrossFade())
        .into(this)
}