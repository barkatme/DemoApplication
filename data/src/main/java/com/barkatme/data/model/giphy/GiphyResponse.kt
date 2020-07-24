package com.barkatme.data.model.giphy

import kotlinx.serialization.Serializable


@Serializable
data class GiphyResponse(
        val listGifData: List<GifData>,
        val pagination: Pagination
)