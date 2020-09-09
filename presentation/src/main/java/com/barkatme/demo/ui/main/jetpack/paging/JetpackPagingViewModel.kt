// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.jetpack.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.barkatme.data.model.giphy.Gif
import com.barkatme.demo.data.room.giphy.GifDao
import com.barkatme.demo.data.room.giphy.toGif

class JetpackPagingViewModel(
    private val gifDao: GifDao
) : ViewModel() {

    companion object {
        const val PAGE_SIZE = 10
        const val PREFETCH_DISTANCE = 20
    }

    var gifAllList: LiveData<PagedList<Gif>>
    var filterTextAll = MutableLiveData<String>()

    init {
        gifAllList = Transformations.switchMap<String, PagedList<Gif>>(filterTextAll)
        { input: String ->
            LivePagedListBuilder<Int, Gif>(
                if (input == "" || input == "%%") {
                    gifDao.loadAllGifs()
                } else {
                    gifDao.loadAllGifsByTitle(input)
                }.map { it.toGif() }, PagedList.Config.Builder()
                    .setPageSize(PAGE_SIZE)
                    .setPrefetchDistance(PREFETCH_DISTANCE)
                    .build()
            ).build()
        }
    }
}