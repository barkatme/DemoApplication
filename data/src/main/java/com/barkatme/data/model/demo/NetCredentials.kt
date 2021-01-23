package com.barkatme.data.model.demo

import com.barkatme.demo.domain.model.demo.Credentials
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
class NetCredentials(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String
)

fun Credentials.toNetModel() = NetCredentials(email, password)

fun NetCredentials.toDomainModel() = Credentials(email, password)

fun NetCredentials.asJson(json: Json): String =
    json.encodeToString(NetCredentials.serializer(), this)

fun String.asNetCredentials(json: Json): NetCredentials =
    json.decodeFromString(NetCredentials.serializer(), this)