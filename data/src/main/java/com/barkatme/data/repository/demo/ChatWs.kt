package com.barkatme.data.repository.demo

import com.barkatme.data.model.demo.asJson
import com.barkatme.data.model.demo.asNetMessage
import com.barkatme.data.model.demo.toDomainModel
import com.barkatme.data.model.demo.toNetModel
import com.barkatme.demo.domain.model.demo.Message
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ChatWs(private val client: HttpClient, private val json: Json) {

    var isConnected = false
        private set

    val incomingMessages: MutableStateFlow<Message> = MutableStateFlow(Message())
    private val outgoingMessages: MutableStateFlow<Message> = MutableStateFlow(Message())

    init {
        GlobalScope.launch {
            connect()
        }
    }

    suspend fun connect() {
        try {
            client.ws(
                host = "barkatme-demo.herokuapp.com",
                path = "/chat"
            ) {
                isConnected = true

                launch {
                    outgoingMessages
                        .collect {
                            send(it.toNetModel().asJson(json))
                        }
                }
                incoming.consumeAsFlow()
                    .flowOn(Dispatchers.IO)
                    .map { it.readBytes().decodeToString() }
                    .map { it.asNetMessage(json).toDomainModel() }
                    .collect { incomingMessages.emit(it) }

                isConnected = false
            }
        } finally {
        }
    }

    suspend fun newMessage(message: Message) = outgoingMessages.emit(message)

}