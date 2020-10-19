// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.room.giphy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkatme.demo.domain.interactor.giphy.SearchGifsInteractor
import com.barkatme.demo.domain.interactor.giphy.TrendingGifsInteractor
import com.barkatme.demo.domain.model.giphy.Gif
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class RoomGiphyViewModel(
    private val trendingGifsInteractor: TrendingGifsInteractor,
    private val searchGifsInteractor: SearchGifsInteractor
) : ViewModel() {

    companion object {
        const val SEARCH_TIMEOUT = 1600L
    }

    fun onGifClick(gif: Gif) {
        Log.d("clicked gif", gif.toString())
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun createGifsLiveData(searchStateFlow: StateFlow<String>): LiveData<List<Gif>> =
        searchStateFlow.debounce(SEARCH_TIMEOUT)
            .distinctUntilChanged()
            .flatMapLatest { queue ->
                flow {
                    emit(
                        if (queue.isNotBlank()) {
                            searchGifsInteractor.get(queue)
                        } else {
                            trendingGifsInteractor.get()
                        }
                    )
                }
            }
            .asLiveData(Dispatchers.Main)

}