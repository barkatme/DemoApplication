// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data.repository.giphy

import com.barkatme.data.model.giphy.Gif

interface GiphyLocalRepository {
    suspend fun trending(): List<Gif>
    suspend fun saveTrending(gifs: List<Gif>)
}