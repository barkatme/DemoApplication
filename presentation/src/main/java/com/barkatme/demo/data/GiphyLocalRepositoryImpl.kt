// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.data

import com.barkatme.data.model.giphy.*
import com.barkatme.data.repository.giphy.GiphyLocalRepository
import com.barkatme.demo.data.room.giphy.GifDao

class GiphyLocalRepositoryImpl(val gifDao: GifDao) : GiphyLocalRepository {
    override suspend fun trending(): GiphyResponse = GiphyResponse(

        //temporary variant
        listOf(
            GifData(
                "", "testItem", Images(
                    Original(
                        "",
                        "",
                        "",
                        null,
                        null,
                        "",
                        "https://media.giphy.com/media/gw3IWyGkC0rsazTi/giphy.gif",
                        null,
                        null,
                        ""
                    )
                    , Preview()
                )
            )
        ), Pagination()
    )

    override suspend fun saveTrending(giphyResponse: GiphyResponse) {
    }
}