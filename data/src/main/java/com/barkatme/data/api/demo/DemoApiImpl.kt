package com.barkatme.data.api.demo

import com.barkatme.data.model.demo.asJson
import com.barkatme.data.model.demo.asNetToken
import com.barkatme.data.model.demo.toDomainModel
import com.barkatme.data.model.demo.toNetModel
import com.barkatme.data.repository.demo.ChatWs
import com.barkatme.demo.domain.api.DemoApi
import com.barkatme.demo.domain.model.demo.Credentials
import com.barkatme.demo.domain.model.demo.Message
import com.barkatme.demo.domain.model.demo.Token
import com.barkatme.demo.domain.repository.TokenRepository
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.coroutines.awaitString
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class DemoApiImpl(
    private val json: Json,
    private val tokenRepository: TokenRepository,
    private val chatWs: ChatWs
) : DemoApi {

    override suspend fun signIn(credentials: Credentials): Token = withContext(Dispatchers.IO) {
        DemoApi.URL.signIn.httpPost()
            .header("Content-Type" to "application/json")
            .body(credentials.toNetModel().asJson(json))
            .awaitString()
            .asNetToken(json)
            .toDomainModel()
    }

    override suspend fun signUp(credentials: Credentials): Token = withContext(Dispatchers.IO) {
        DemoApi.URL.signUp.httpPost()
            .header("Content-Type" to "application/json")
            .body(credentials.toNetModel().asJson(json))
            .awaitString()
            .asNetToken(json)
            .toDomainModel()
    }

    override suspend fun signOut(): Token = withContext(Dispatchers.IO) {
        DemoApi.URL.signOut.httpGet()
            .demoAuth()
            .awaitString()
            .asNetToken(json)
            .toDomainModel()
    }

    override suspend fun chatMessagesFlow(): Flow<Message> {
        chatWs.connect()
        return chatWs.incomingMessages
    }

    override suspend fun newMessage(message: Message) {
        chatWs.connect()
        chatWs.newMessage(message)
    }

    override suspend fun getCurrentUser(): String = withContext(Dispatchers.IO) {
        DemoApi.URL.currentUser.httpGet()
            .demoAuth()
            .awaitString()
    }

    private suspend fun Request.demoAuth() =
        authentication().bearer(tokenRepository.requireToken().token)

}