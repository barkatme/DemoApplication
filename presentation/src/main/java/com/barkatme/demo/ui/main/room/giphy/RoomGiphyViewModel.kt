// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.room.giphy

import androidx.lifecycle.ViewModel
import com.barkatme.data.model.giphy.GifData
import com.barkatme.demo.domain.interactor.giphy.TrendingGifsInteractor

class RoomGiphyViewModel(private val trendingGifsInteractor: TrendingGifsInteractor) : ViewModel() {

    suspend fun loadTrending() = trendingGifsInteractor.get().data

    fun onGifClick(gifData: GifData) {
        print(gifData)
    }

}