// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.domain.interactor.giphy

import com.barkatme.demo.domain.repository.GiphyRepository

class TrendingGifsInteractor(private val repository: GiphyRepository) {
    suspend fun get(offset: Int? = null, limit: Int? = null, rating: String? = null) =
        repository.trending(offset, limit, rating)
}