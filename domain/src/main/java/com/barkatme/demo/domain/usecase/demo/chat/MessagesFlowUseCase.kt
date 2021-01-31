package com.barkatme.demo.domain.usecase.demo.chat

import com.barkatme.demo.domain.model.demo.Message
import com.barkatme.demo.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class MessagesFlowUseCase(private val chatRepository: ChatRepository) {
    suspend fun getMessagesFlow(): Flow<Message> = chatRepository.getMessagesFlow()
}