// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.repository.giphy

import com.barkatme.data.model.giphy.Gif
import com.barkatme.data.model.giphy.toGif
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
    ): List<Gif> = withContext(Dispatchers.IO) {
        var gifs: List<Gif>
        try {
            gifs = remote.trending(offset, limit, rating).data.map { it.toGif() }
            local.save(gifs)
        } catch (fuelError: FuelError) {
            gifs = local.getAll()
        }
        return@withContext gifs
    }

    suspend fun search(
        queue: String,
        offset: Int? = null,
        limit: Int? = null,
        rating: String? = null
    ): List<Gif> = withContext(Dispatchers.IO) {
        var gifs: List<Gif>
        try {
            gifs = remote.search(queue, offset, limit, rating).data.map { it.toGif() }
            local.save(gifs)
        } catch (fuelError: FuelError) {
            gifs = local.search(queue)
        }
        return@withContext gifs
    }
}