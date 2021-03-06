package com.barkatme.data.model.giphy.remote

import kotlinx.serialization.Serializable

@Serializable
data class GifData(
        val id: String,
        val title: String,
        val images: Images
)