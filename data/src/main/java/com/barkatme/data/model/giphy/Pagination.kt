package com.barkatme.data.model.giphy


import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    val totalCount: Int? = null,
    val count: Int? = null,
    val offset: Int? = null
)