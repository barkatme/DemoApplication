package com.barkatme.data.model.demo

import com.barkatme.demo.domain.model.demo.Message
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class NetMessage(
    @SerialName("id") val id: Int = 0,
    @SerialName("nickName") val nickName: String = "",
    @SerialName("time") val time: Long = System.currentTimeMillis(),
    @SerialName("text") val text: String = "",
)

fun Message.toNetModel() = NetMessage(id, nickName, time, text)

fun NetMessage.toDomainModel() = Message(id, nickName, time, text)

fun NetMessage.asJson(json: Json): String =
    json.encodeToString(NetMessage.serializer(), this)

fun String.asNetMessage(json: Json): NetMessage =
    json.decodeFromString(NetMessage.serializer(), this)