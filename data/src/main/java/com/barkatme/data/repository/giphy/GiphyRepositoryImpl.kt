// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.repository.giphy

import com.barkatme.demo.domain.data.api.GiphyApi
import com.barkatme.demo.domain.data.repository.GiphyLocalRepository
import com.barkatme.demo.domain.data.repository.GiphyRepository
import com.barkatme.demo.domain.model.giphy.Gif
import com.github.kittinunf.fuel.core.FuelError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GiphyRepositoryImpl(
    override val localRepository: GiphyLocalRepository,
    override val api: GiphyApi
) : GiphyRepository {

    override suspend fun trending(
        offset: Int?,
        limit: Int?,
        rating: String?
    ) = withContext(Dispatchers.IO) {
        var gifs: List<Gif>
        try {
            gifs = api.trending(offset, limit, rating)
            localRepository.save(gifs)
        } catch (fuelError: FuelError) {
            gifs = localRepository.getAll()
        }
        return@withContext gifs
    }

    override suspend fun search(
        queue: String,
        offset: Int?,
        limit: Int?,
        rating: String?
    ) = withContext(Dispatchers.IO) {
        var gifs: List<Gif>
        try {
            gifs = api.search(queue, offset, limit, rating)
            localRepository.save(gifs)
        } catch (fuelError: FuelError) {
            gifs = localRepository.search(queue)
        }
        return@withContext gifs
    }
}