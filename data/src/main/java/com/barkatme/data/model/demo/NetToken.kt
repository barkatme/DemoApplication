package com.barkatme.data.model.demo

import com.barkatme.demo.domain.model.demo.Token
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class NetToken(
    @SerialName("token") val token: String
)

fun NetToken.toDomainModel() = Token(token)
fun Token.toNetModel() = NetToken(token)

fun NetToken.asJson(json: Json) = json.encodeToString(NetToken.serializer(), this)
fun String.asNetToken(json: Json) = json.decodeFromString(NetToken.serializer(), this)