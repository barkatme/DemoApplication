package com.barkatme.data

import com.barkatme.data.repository.GiphyRepository
import com.barkatme.data.repository.NetRepository
import com.barkatme.data.repository.Repository
import com.barkatme.data.repository.flow.FlowRepository
import com.barkatme.data.repository.flow.NetFlowRepository
import org.koin.core.context.startKoin
import org.koin.dsl.module


//override allow other modules to make other providers for definitions in that module
//it can also be applied for definitions
val dataModule = module(override = true) {
    // declare single instance for NetRepository class
    // Will match type NetRepository only if without "bind"
    // bind make it matches 2 types: NetRepository & Repository
    single(createdAtStart = true) { NetRepository() } //bind Repository::class

    // declare single instance for Repository implementation
    // Will match type Repository only
    single<Repository> { NetRepository() }
    //or this way -> single { NetRepository() as Repository } (not preferred, as koin docs said)

    single<FlowRepository> { NetFlowRepository() }

    single { GiphyRepository() }
}

val startDataKoin = {
    startKoin {
        modules(dataModule)
    }
}