// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.room.giphy

import android.util.Log
import androidx.lifecycle.ViewModel
import com.barkatme.data.model.giphy.Gif
import com.barkatme.demo.domain.interactor.giphy.TrendingGifsInteractor

class RoomGiphyViewModel(private val trendingGifsInteractor: TrendingGifsInteractor) : ViewModel() {

    suspend fun loadTrending() = trendingGifsInteractor.get()

    fun onGifClick(gif: Gif) {
        Log.d("clicked gif", gif.toString())
    }

}