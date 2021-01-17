// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.domain.repository

import com.barkatme.demo.domain.api.GiphyApi
import com.barkatme.demo.domain.model.giphy.Gif

interface GiphyRepository {

    val localRepository: GiphyLocalRepository
    val api: GiphyApi


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