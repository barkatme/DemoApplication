// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.api.giphy

import com.barkatme.data.api.log
import com.barkatme.data.model.giphy.remote.giphyResponseSerializer
import com.barkatme.data.model.giphy.toDomainModel
import com.barkatme.data.model.giphy.toGif
import com.barkatme.demo.domain.api.GiphyApi
import com.barkatme.demo.domain.model.giphy.Gif
import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class GiphyApiImpl(private val json: Json) : GiphyApi {

    override suspend fun trending(
        offset: Int?,
        limit: Int?,
        rating: String?
    ): List<Gif> {
        return withContext(Dispatchers.IO) {
            val parameters = mutableListOf(GiphyApi.API_KEY_PARAMETER)
            offset?.let { parameters.add(Pair(GiphyApi.OFFSET_PARAMETER, it.toString())) }
            limit?.let { parameters.add(Pair(GiphyApi.LIMIT_PARAMETER, it.toString())) }
            rating?.let { parameters.add(Pair(GiphyApi.RATING_PARAMETER, it)) }
            "${GiphyApi.BASE_URL}trending".httpGet(parameters)
                .log()
                .await(
                    kotlinxDeserializerOf(giphyResponseSerializer, json)
                )
                .data.map { it.toGif().toDomainModel() }
        }
    }

    override suspend fun search(
        queue: String,
        offset: Int?,
        limit: Int?,
        rating: String?
    ): List<Gif> = withContext(Dispatchers.IO) {
        val parameters = mutableListOf(GiphyApi.API_KEY_PARAMETER)
        parameters.add(Pair(GiphyApi.QUEUE_PARAMETER, queue))
        offset?.let { parameters.add(Pair(GiphyApi.OFFSET_PARAMETER, it.toString())) }
        limit?.let { parameters.add(Pair(GiphyApi.LIMIT_PARAMETER, it.toString())) }
        rating?.let { parameters.add(Pair(GiphyApi.RATING_PARAMETER, it)) }
        "${GiphyApi.BASE_URL}search".httpGet(parameters)
            .log()
            .await(
                kotlinxDeserializerOf(giphyResponseSerializer, json)
            )
            .data.map { it.toGif().toDomainModel() }
    }

}