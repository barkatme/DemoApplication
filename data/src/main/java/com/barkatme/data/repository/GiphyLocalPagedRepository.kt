// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.repository

import androidx.paging.DataSource
import com.barkatme.data.repository.room.giphy.GifDao
import com.barkatme.data.repository.room.giphy.toDomainGif
import com.barkatme.demo.domain.model.giphy.Gif

class GiphyLocalPagedRepository(private val gifDao: GifDao) {
    fun loadAllGifs(): DataSource.Factory<Int, Gif> =
        gifDao.loadAllGifs().map { it.toDomainGif() }

    fun loadAllGifsByTitle(name: String): DataSource.Factory<Int, Gif> =
        gifDao.loadAllGifsByTitle(name).map { it.toDomainGif() }
}