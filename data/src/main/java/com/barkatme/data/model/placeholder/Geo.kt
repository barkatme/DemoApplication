package com.barkatme.data.model.placeholder

import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    val lat: String,
    val lng: String
) {
    companion object {
        @JvmStatic
        val serializable = serializer()
    }
}