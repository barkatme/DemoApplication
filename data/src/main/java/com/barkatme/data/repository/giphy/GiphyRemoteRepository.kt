// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.repository.giphy

import com.barkatme.data.model.giphy.remote.GiphyResponse
import com.barkatme.data.model.giphy.remote.giphyResponseSerializer
import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class GiphyRemoteRepository {
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
        const val API_KEY = "3gSLYjFzyfEBSiZ60ceDOjiwjU4WVluT"
        const val defaultRating =
            Ratings.G
        const val DEFAULT_OFFSET = 0
        const val DEFAULT_LIMIT = 10
        const val DEFAULT_LANGUAGE = "en"

        const val API_KEY_KEY = "api_key"
        const val QUEUE_KEY = "q"
        const val LIMIT_KEY = "limit"
        const val OFFSET_KEY = "offset"
        const val RATING_KEY = "rating"
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private val json = Json(JsonConfiguration(ignoreUnknownKeys = true))

    suspend fun trending(
        offset: Int? = null,
        limit: Int? = null,
        rating: String? = null
    ): GiphyResponse = withContext(Dispatchers.IO) {
        val parameters = mutableListOf(
            Pair(
                API_KEY_KEY,
                API_KEY
            )
        )
        offset?.let { parameters.add(Pair(OFFSET_KEY, it.toString())) }
        limit?.let { parameters.add(Pair(LIMIT_KEY, it.toString())) }
        rating?.let { parameters.add(Pair(RATING_KEY, it)) }
        "${BASE_URL}trending".httpGet(parameters)
            .await(
                kotlinxDeserializerOf(giphyResponseSerializer, json)
            )
    }

    suspend fun search(
        queue: String,
        offset: Int? = null,
        limit: Int? = null,
        rating: String? = null
    ): GiphyResponse = withContext(Dispatchers.IO) {
        val parameters = mutableListOf(
            Pair(
                API_KEY_KEY,
                API_KEY
            )
        )
        parameters.add(Pair(QUEUE_KEY, queue))
        offset?.let { parameters.add(Pair(OFFSET_KEY, it.toString())) }
        limit?.let { parameters.add(Pair(LIMIT_KEY, it.toString())) }
        rating?.let { parameters.add(Pair(RATING_KEY, it)) }
        "${BASE_URL}search".httpGet(parameters)
            .await(
                kotlinxDeserializerOf(giphyResponseSerializer, json)
            )
    }

}