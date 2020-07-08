package com.barkatme.demo

import android.app.Application
import com.barkatme.data.dataModule
import com.barkatme.demo.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

val presentationModule = module {

}

val startPresentationKoin = { application: Application ->
    startKoin {
        androidContext(application)
        modules(dataModule, domainModule, presentationModule)
        logger(AndroidLogger())
    }
}