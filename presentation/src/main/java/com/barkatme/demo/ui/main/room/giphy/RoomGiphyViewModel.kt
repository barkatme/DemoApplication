// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.room.giphy

import androidx.lifecycle.ViewModel
import com.barkatme.demo.domain.interactor.giphy.TrendingGifsInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RoomGiphyViewModel(private val trendingGifsInteractor: TrendingGifsInteractor) : ViewModel(),
    CoroutineScope {
    fun loadTrending() {
        launch {
            trendingGifsInteractor.get().let {
                println("GIFS ARE LOADED")
                println(it)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job = Job()

}