package com.barkatme.demo.domain.repository

import com.barkatme.demo.domain.model.demo.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getMessagesFlow(): Flow<Message>
    suspend fun newMessage(message: Message)
}