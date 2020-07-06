package com.barkatme.demo

import android.app.Application
import com.barkatme.data.data_module
import com.barkatme.demo.domain.domain_module
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

val presentation_module = module {

}

val startPresentationModule = { application: Application ->
    startKoin {
        androidContext(application)
        modules(data_module, domain_module, presentation_module)
        logger(AndroidLogger())
    }
}