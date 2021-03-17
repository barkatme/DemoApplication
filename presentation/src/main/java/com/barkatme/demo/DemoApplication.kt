package com.barkatme.demo

import android.app.Application
import com.barkatme.demo.ui.main.startCounter
import kotlinx.coroutines.*

@FlowPreview
@ExperimentalCoroutinesApi
@Suppress("unused")
class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startPresentationKoin(this@DemoApplication)
        startCounter()
    }
}