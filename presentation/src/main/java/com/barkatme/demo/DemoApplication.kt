package com.barkatme.demo

import android.app.Application
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import org.koin.android.ext.android.inject

@FlowPreview
@ExperimentalCoroutinesApi
class DemoApplication : Application() {

    companion object {
        const val DELAY = 5
    }

    override fun onCreate() {
        super.onCreate()
        startPresentationKoin(this@DemoApplication)
        startCounter()
    }

    private fun startCounter() {
        GlobalScope.launch {
            val channel = inject<Channel<Long>>()
            var counter = 0L
            while (true) {
                channel.value.offer(counter++ * DELAY)
                delay(DELAY * 1000L)
            }
        }
    }
}