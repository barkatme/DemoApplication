package com.barkatme.data.model.giphy

import kotlinx.serialization.Serializable

@Serializable
data class Original(
    val frames: String,
    val hash: String,
    val height: String,
    val mp4: String,
    val mp4Size: String,
    val size: String,
    val url: String,
    val webp: String,
    val webpSize: String,
    val width: String
)