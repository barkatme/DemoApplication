// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.repository

import com.barkatme.data.model.giphy.toDataModel
import com.barkatme.data.repository.room.giphy.GifDao
import com.barkatme.data.repository.room.giphy.toDomainGif
import com.barkatme.data.repository.room.giphy.toLocalGif
import com.barkatme.demo.domain.data.repository.GiphyLocalRepository
import com.barkatme.demo.domain.model.giphy.Gif

class GiphyLocalRepositoryImpl(private val gifDao: GifDao) : GiphyLocalRepository {
    override suspend fun getAll(): List<Gif> =
        gifDao.getGifs().map { it.toDomainGif() }

    override suspend fun save(gifs: List<Gif>) {
        gifs.forEach { gif -> gifDao.insertGif(gif.toDataModel().toLocalGif()) }
    }

    override suspend fun search(queue: String): List<Gif> =
        getAll().filter { it.title.contains(queue, true) }
}