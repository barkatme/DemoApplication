// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.repository.giphy

import com.barkatme.data.model.giphy.GiphyResponse

interface GiphyLocalRepository {
    suspend fun trending(): GiphyResponse
    suspend fun saveTrending(giphyResponse: GiphyResponse)
}