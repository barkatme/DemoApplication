package com.barkatme.demo.domain

import com.barkatme.data.data_module
import org.koin.core.context.startKoin
import org.koin.dsl.module

val domain_module = module {
    
}

val start_domain_koin = {
    startKoin {
        modules(data_module, domain_module)
    }
}