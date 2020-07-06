package com.barkatme.data

import org.koin.dsl.module

val data_module = module {
    single { NetRepository() }
}