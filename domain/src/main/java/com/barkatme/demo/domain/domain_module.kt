package com.barkatme.demo.domain

import org.koin.dsl.module

val domain_module = module {
    factory { UserCreator() }
}