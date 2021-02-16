package com.barkatme.data.model.placeholder

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    @Suppress("SpellCheckingInspection")
    @SerialName("zipcode")
    val zipCode: String
) {
    companion object {
        @JvmStatic
        val serializer = serializer()
    }
}