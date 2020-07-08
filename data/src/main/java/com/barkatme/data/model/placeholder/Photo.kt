package com.barkatme.data.model.placeholder

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
){
    companion object{
        @JvmStatic
        val serializer = serializer()
    }
}