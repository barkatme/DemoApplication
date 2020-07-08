package com.barkatme.data.model.placeholder

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) {
    companion object {
        @JvmStatic
        val serializer = serializer()
    }
}