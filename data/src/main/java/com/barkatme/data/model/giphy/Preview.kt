package com.barkatme.data.model.giphy

import kotlinx.serialization.Serializable

@Serializable
data class Preview(
    val height: String,
    val mp4: String,
    val mp4Size: String,
    val width: String
)