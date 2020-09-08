package com.barkatme.data

import com.barkatme.data.repository.flow.FlowRepository
import com.barkatme.data.repository.flow.NetFlowRepository
import com.barkatme.data.repository.giphy.GiphyRemoteRepository
import com.barkatme.data.repository.giphy.GiphyRepository
import com.barkatme.data.repository.jsonplaceholder.JPRepository
import com.barkatme.data.repository.jsonplaceholder.NetJPRepository
import org.koin.core.context.startKoin
import org.koin.dsl.module


//override allow other modules to make other providers for definitions in that module
//it can also be applied for definitions
val dataModule = module(override = true) {
    // declare single instance for NetRepository class
    // Will match type NetRepository only if without "bind"
    // bind make it matches 2 types: NetRepository & Repository
    single(createdAtStart = true) { NetJPRepository() } //bind Repository::class

    // declare single instance for Repository implementation
    // Will match type Repository only
    single<JPRepository> { NetJPRepository() }
    //or this way -> single { NetRepository() as Repository } (not preferred, as koin docs said)

    single<FlowRepository> { NetFlowRepository() }

    single { GiphyRemoteRepository() }
    single { GiphyRepository(get(), get()) }
}

val startDataKoin = {
    startKoin {
        modules(dataModule)
    }
}