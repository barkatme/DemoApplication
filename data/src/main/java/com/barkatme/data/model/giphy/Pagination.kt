package com.barkatme.data.model.giphy


import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    val totalCount: Int,
    val count: Int,
    val offset: Int
)