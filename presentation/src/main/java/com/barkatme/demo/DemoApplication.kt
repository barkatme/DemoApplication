package com.barkatme.demo

import android.app.Application

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startPresentationModule(this@DemoApplication)
    }
}