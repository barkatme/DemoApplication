// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.domain.api

import com.barkatme.demo.domain.model.giphy.Gif

interface GiphyApi {


    companion object {

        class Ratings {
            companion object {
                const val G = "G"
                const val PG = "PG"
                const val PG13 = "PG-13"
                const val R = "R"
            }
        }

        const val BASE_URL = "https://api.giphy.com/v1/gifs/"
        private const val API_KEY = "3gSLYjFzyfEBSiZ60ceDOjiwjU4WVluT"
        const val defaultRating = Ratings.G
        const val DEFAULT_OFFSET = 0
        const val DEFAULT_LIMIT = 10
        const val DEFAULT_LANGUAGE = "en"

        private const val API_KEY_PARAMETER_NAME = "api_key"
        const val QUEUE_PARAMETER = "q"
        const val LIMIT_PARAMETER = "limit"
        const val OFFSET_PARAMETER = "offset"
        const val RATING_PARAMETER = "rating"

        val API_KEY_PARAMETER: Pair<String, String> = Pair(API_KEY_PARAMETER_NAME, API_KEY)
    }

    suspend fun search(
        queue: String,
        offset: Int? = null,
        limit: Int? = null,
        rating: String? = null
    ): List<Gif>


    suspend fun trending(
        offset: Int? = null,
        limit: Int? = null,
        rating: String? = null
    ): List<Gif>
}