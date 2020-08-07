package com.barkatme.data.model.giphy

import kotlinx.serialization.Serializable

@Serializable
data class Original(
    val frames: String,
    val hash: String,
    val height: String,
    val mp4: String? = null,
    val mp4Size: String? = null,
    val size: String,
    val url: String,
    val webp: String? = null,
    val webpSize: String? = null,
    val width: String
)