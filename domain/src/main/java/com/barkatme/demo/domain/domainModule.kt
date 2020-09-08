package com.barkatme.demo.domain

import com.barkatme.data.dataModule
import com.barkatme.demo.domain.interactor.flow.TestFlowInteractor
import com.barkatme.demo.domain.interactor.giphy.SearchGifsInteractor
import com.barkatme.demo.domain.interactor.giphy.TrendingGifsInteractor
import org.koin.core.context.startKoin
import org.koin.dsl.module

val domainModule = module {
    single { TestFlowInteractor(get()) }
    single { TrendingGifsInteractor(get()) }
    single { SearchGifsInteractor(get()) }
}

val startDomainKoin = {
    startKoin {
        modules(dataModule, domainModule)
    }
}