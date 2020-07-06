package com.barkatme.data

import org.koin.core.context.startKoin
import org.koin.dsl.module

val data_module = module {
    single { NetRepository() }
}

val start_data_koin = {
    startKoin {
        modules(data_module)
    }
}