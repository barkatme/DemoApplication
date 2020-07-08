package com.barkatme.data.model.placeholder

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
){
    companion object{
        @JvmStatic
        val serializer = serializer()
    }
}