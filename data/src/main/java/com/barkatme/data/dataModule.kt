package com.barkatme.data


import com.barkatme.data.api.giphy.GiphyApiImpl
import com.barkatme.data.api.placeholder.PlaceholderApiImpl
import com.barkatme.data.api.placeholder.PlaceholderFlowApiImpl
import com.barkatme.demo.domain.data.api.GiphyApi
import com.barkatme.demo.domain.data.api.PlaceholderApi
import com.barkatme.demo.domain.data.api.PlaceholderFlowApi
import com.barkatme.demo.domain.domainModule
import org.koin.core.context.startKoin
import org.koin.dsl.module


//override allow other modules to make other providers for definitions in that module
//it can also be applied for definitions
val dataModule = module(override = true) {
    // declare single instance for NetRepository class
    // Will match type NetRepository only if without "bind"
    // bind make it matches 2 types: NetRepository & Repository
    single(createdAtStart = true) { PlaceholderApiImpl() } //bind Repository::class

    // declare single instance for Repository implementation
    // Will match type Repository only
    single<PlaceholderApi> { PlaceholderApiImpl() }
    //or this way -> single { NetRepository() as Repository } (not preferred, as koin docs said)

    single<PlaceholderFlowApi> { PlaceholderFlowApiImpl() }

    single<GiphyApi> { GiphyApiImpl() }
}

val startDataKoin = {
    startKoin {
        modules(domainModule, dataModule)
    }
}