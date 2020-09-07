// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.repository.giphy

import com.barkatme.data.model.giphy.GiphyResponse
import com.github.kittinunf.fuel.core.FuelError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GiphyRepository(
    val local: GiphyLocalRepository,
    val remote: GiphyRemoteRepository
) {

    suspend fun trending(
        offset: Int? = null,
        limit: Int? = null,
        rating: String? = null
    ): GiphyResponse = withContext(Dispatchers.IO) {
        var gifs: GiphyResponse
        try {
            gifs = remote.trending(offset, limit, rating)
            local.saveTrending(gifs)
        } catch (fuelError: FuelError) {
            gifs = local.trending()
        }
        return@withContext gifs
    }
}