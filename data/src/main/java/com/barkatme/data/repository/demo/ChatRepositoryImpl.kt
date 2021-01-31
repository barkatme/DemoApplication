package com.barkatme.data.repository.demo

import com.barkatme.demo.domain.api.DemoApi
import com.barkatme.demo.domain.model.demo.Message
import com.barkatme.demo.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class ChatRepositoryImpl(private val demoApi: DemoApi) : ChatRepository {

    override suspend fun getMessagesFlow(): Flow<Message> = demoApi.chatMessagesFlow()

    override suspend fun newMessage(message: Message) {
        demoApi.newMessage(message)
    }

}