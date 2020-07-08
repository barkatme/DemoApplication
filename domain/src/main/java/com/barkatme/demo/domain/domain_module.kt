package com.barkatme.demo.domain

import com.barkatme.data.dataModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

val domainModule = module {
    
}

val startDomainKoin = {
    startKoin {
        modules(dataModule, domainModule)
    }
}