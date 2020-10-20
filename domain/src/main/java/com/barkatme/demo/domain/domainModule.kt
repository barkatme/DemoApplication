package com.barkatme.demo.domain

import com.barkatme.demo.domain.data.repository.GiphyRepository
import com.barkatme.demo.domain.data.repository.GiphyRepositoryImpl
import com.barkatme.demo.domain.interactor.flow.TestFlowInteractor
import com.barkatme.demo.domain.interactor.giphy.SearchGifsInteractor
import com.barkatme.demo.domain.interactor.giphy.TrendingGifsInteractor
import org.koin.core.context.startKoin
import org.koin.dsl.module

val domainModule = module {
    single { TestFlowInteractor(get()) }
    single { TrendingGifsInteractor(get()) }
    single { SearchGifsInteractor(get()) }
    single<GiphyRepository> { GiphyRepositoryImpl(get(), get()) }
}

val startDomainKoin = {
    startKoin {
        modules(domainModule)
    }
}