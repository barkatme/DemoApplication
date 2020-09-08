// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.data

import com.barkatme.data.model.giphy.Gif
import com.barkatme.data.repository.giphy.GiphyLocalRepository
import com.barkatme.demo.data.room.giphy.GifDao
import com.barkatme.demo.data.room.giphy.toGif
import com.barkatme.demo.data.room.giphy.toLocalGif

class GiphyLocalRepositoryImpl(val gifDao: GifDao) : GiphyLocalRepository {
    override suspend fun trending(): List<Gif> =
        gifDao.getGifs().map { it.toGif() }

    override suspend fun saveTrending(gifs: List<Gif>) {
        gifs.forEach { gif -> gifDao.insertGif(gif.toLocalGif()) }
    }
}