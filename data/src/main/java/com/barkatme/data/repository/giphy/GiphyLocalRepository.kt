// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.repository.giphy

import com.barkatme.data.model.giphy.Gif

interface GiphyLocalRepository {
    suspend fun getAll(): List<Gif>
    suspend fun save(gifs: List<Gif>)
    suspend fun search(queue: String): List<Gif>
}