package com.barkatme.demo.ui.main

import com.barkatme.demo.DemoApplication
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import org.koin.android.ext.android.inject


private object CounterConsts {
    const val DELAY = 5
}

@ExperimentalCoroutinesApi
@FlowPreview
fun DemoApplication.startCounter() {
    GlobalScope.launch {
        val channel = inject<Channel<Long>>()
        var counter = 0L
        while (true) {
            channel.value.offer(counter++ * CounterConsts.DELAY)
            delay(CounterConsts.DELAY * 1000L)
        }
    }
}