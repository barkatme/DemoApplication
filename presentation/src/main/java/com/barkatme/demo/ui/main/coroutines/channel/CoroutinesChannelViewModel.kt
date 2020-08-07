package com.barkatme.demo.ui.main.coroutines.channel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class CoroutinesChannelViewModel : ViewModel(), CoroutineScope {

    companion object {
        const val MAX_LINES = 10
    }

    private var counter = 0

    private val channel = Channel<String>(MAX_LINES)
    val output = MutableLiveData<String>()
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + CoroutineExceptionHandler { _, throwable ->
            output.value += "\n" + throwable.message
        }

    init {
        launch {
            output.value = "press START button twice with some delay"
            delay(2000)
            output.value = ""
        }
        launch {
            while (!channel.isClosedForReceive) {
                val next = withContext(Dispatchers.Default) { channel.receive() }
                output.value += "\n" + next
            }
            output.value += "\ndone"
        }
    }

    fun start() {
        val index = counter++
        launch {
            for (i in 0..MAX_LINES) {
                delay(700)
                channel.send(" ".repeat(index * 3) + "$i")
            }
            channel.close()
        }
    }
}