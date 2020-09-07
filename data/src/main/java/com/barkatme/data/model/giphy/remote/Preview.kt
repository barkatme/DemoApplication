package com.barkatme.data.model.giphy.remote

import kotlinx.serialization.Serializable

@Serializable
data class Preview(
    val height: String? = null,
    val mp4: String? = null,
    val mp4Size: String? = null,
    val width: String? = null
)