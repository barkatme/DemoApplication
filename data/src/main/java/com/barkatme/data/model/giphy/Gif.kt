// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.model.giphy

import com.barkatme.data.model.giphy.remote.GifData

data class Gif(
    val id: String,
    val title: String? = null,
    val url: String? = null,
    val previewUrl: String? = null
)

fun GifData.toGif() = Gif(id, title, images.original.url, images.preview.mp4)