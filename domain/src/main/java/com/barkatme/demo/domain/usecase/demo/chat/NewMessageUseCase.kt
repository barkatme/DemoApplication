package com.barkatme.demo.domain.usecase.demo.chat

import com.barkatme.demo.domain.model.demo.Message
import com.barkatme.demo.domain.repository.ChatRepository

class NewMessageUseCase(private val chatRepository: ChatRepository) {
    suspend fun newMessage(nickName: String, message: String) {
        chatRepository.newMessage(Message(nickName = nickName, text = message))
    }
}