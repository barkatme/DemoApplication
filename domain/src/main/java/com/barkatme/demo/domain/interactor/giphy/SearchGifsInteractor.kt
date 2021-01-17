// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.domain.interactor.giphy

import com.barkatme.demo.domain.repository.GiphyRepository


class SearchGifsInteractor(private val repository: GiphyRepository) {
    suspend fun get(
        queue: String,
        offset: Int? = null,
        limit: Int? = null,
        rating: String? = null
    ) =
        repository.search(queue, offset, limit, rating)
}