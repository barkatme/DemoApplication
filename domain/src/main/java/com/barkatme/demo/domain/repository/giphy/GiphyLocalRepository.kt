// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.domain.repository.giphy

import com.barkatme.demo.domain.model.giphy.Gif

interface GiphyLocalRepository {
    suspend fun getAll(): List<Gif>
    suspend fun save(gifs: List<Gif>)
    suspend fun search(queue: String): List<Gif>
}