// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.room.giphy

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barkatme.data.model.giphy.Gif
import com.barkatme.demo.domain.interactor.giphy.SearchGifsInteractor
import com.barkatme.demo.domain.interactor.giphy.TrendingGifsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RoomGiphyViewModel(
    private val trendingGifsInteractor: TrendingGifsInteractor,
    private val searchGifsInteractor: SearchGifsInteractor
) : ViewModel() {

    companion object {
        const val SEARCH_TIMEOUT = 3000L
    }

    val gifs = MutableLiveData<List<Gif>>()

    private fun loadTrending() {
        viewModelScope.launch {
            val trending = trendingGifsInteractor.get()
            gifs.postValue(trending)
        }
    }

    fun onGifClick(gif: Gif) {
        Log.d("clicked gif", gif.toString())
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun setSearchFlow(searchStateFlow: StateFlow<String>) {
        viewModelScope.launch {
            searchStateFlow.debounce(SEARCH_TIMEOUT)
                .distinctUntilChanged()
                .flatMapLatest { queue ->
                    flow {
                        val received = mutableListOf<Gif>()
                        queue.takeIf { !it.isBlank() }?.let {
                            searchGifsInteractor.get(queue)
                        } ?: run { trendingGifsInteractor.get() }
                            .asFlow()
                            .collect {
                                received.add(it)
                            }
                        emit(received)
                    }
                }
                //makes above operators executing with that Dispatcher
                .flowOn(Dispatchers.Default)
                .collect { result ->
                    gifs.value = result
                }
        }
    }

}