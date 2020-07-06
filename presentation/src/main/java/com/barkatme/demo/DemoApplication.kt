package com.barkatme.demo

import android.app.Application
import com.barkatme.data.data_module
import com.barkatme.demo.domain.domain_module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DemoApplication)
            modules(data_module, domain_module, presentation_module)
        }
    }
}