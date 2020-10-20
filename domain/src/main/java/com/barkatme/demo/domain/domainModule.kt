package com.barkatme.demo.domain

import com.barkatme.demo.domain.interactor.flow.GetTodoFlowInteractor
import com.barkatme.demo.domain.interactor.giphy.SearchGifsInteractor
import com.barkatme.demo.domain.interactor.giphy.TrendingGifsInteractor
import org.koin.core.context.startKoin
import org.koin.dsl.module

val domainModule = module {
    single { GetTodoFlowInteractor(get()) }
    single { TrendingGifsInteractor(get()) }
    single { SearchGifsInteractor(get()) }
}

val startDomainKoin = {
    startKoin {
        modules(domainModule)
    }
}