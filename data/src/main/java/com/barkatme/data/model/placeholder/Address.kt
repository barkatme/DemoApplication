package com.barkatme.data.model.placeholder

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
){
    companion object{
        @JvmStatic
        val serializer = serializer()
    }
}