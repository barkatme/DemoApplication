package com.barkatme.demo

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startPresentationKoin(this@DemoApplication)
    }
}