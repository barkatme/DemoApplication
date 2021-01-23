// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.domain.usecase.giphy

import com.barkatme.demo.domain.repository.giphy.GiphyRepository

class TrendingGifsUseCase(private val repository: GiphyRepository) {
    suspend fun get(offset: Int? = null, limit: Int? = null, rating: String? = null) =
        repository.trending(offset, limit, rating)
}