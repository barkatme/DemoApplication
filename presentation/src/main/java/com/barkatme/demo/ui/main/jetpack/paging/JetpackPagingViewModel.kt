// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.jetpack.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.barkatme.data.repository.firstpdm.giphy.GiphyLocalPagedRepository
import com.barkatme.demo.domain.model.giphy.Gif

class JetpackPagingViewModel(
    private val giphyLocalPagedRepository: GiphyLocalPagedRepository
) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = 20

        private val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .build()
    }

    var gifAllList: LiveData<PagedList<Gif>>
    var filterTextAll = MutableLiveData<String>()

    init {
        gifAllList = Transformations.switchMap<String, PagedList<Gif>>(filterTextAll)
        { input: String ->
            val dataSourceFactory = if (input == "" || input == "%%") {
                giphyLocalPagedRepository.loadAllGifs()
            } else {
                giphyLocalPagedRepository.loadAllGifsByTitle(input)
            }

            LivePagedListBuilder<Int, Gif>(dataSourceFactory, config).build()
        }
    }
}