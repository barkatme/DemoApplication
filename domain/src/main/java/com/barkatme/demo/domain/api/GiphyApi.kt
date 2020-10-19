// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.domain.api

import com.barkatme.demo.domain.model.giphy.Gif

interface GiphyApi {


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