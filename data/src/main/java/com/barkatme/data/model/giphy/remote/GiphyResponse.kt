package com.barkatme.data.model.giphy.remote

import kotlinx.serialization.Serializable


@Serializable
data class GiphyResponse(
    val data: List<GifData>,
    val pagination: Pagination
)