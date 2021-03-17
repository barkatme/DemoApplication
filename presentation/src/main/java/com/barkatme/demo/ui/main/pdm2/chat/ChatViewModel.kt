package com.barkatme.demo.ui.main.pdm2.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barkatme.demo.domain.model.demo.Message
import com.barkatme.demo.domain.usecase.demo.chat.MessagesFlowUseCase
import com.barkatme.demo.domain.usecase.demo.chat.NewMessageUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChatViewModel(
    private val messagesFlowUseCase: MessagesFlowUseCase,
    private val newMessageUseCase: NewMessageUseCase
) : ViewModel() {

    private val messageChannel = Channel<Message>()
    val messages: ReceiveChannel<Message>
        get() = messageChannel

    val nickName: MutableLiveData<String> = MutableLiveData("user")
    val message: MutableLiveData<String> = MutableLiveData("")

    init {
        viewModelScope.launch {
            messagesFlowUseCase.getMessagesFlow().collect {
                messageChannel.send(it)
            }
        }
    }

    fun send() {
        viewModelScope.launch {
            message.value?.let {
                newMessageUseCase.newMessage(
                    nickName.value ?: "unknown user",
                    it
                )
            }
        }
    }


}