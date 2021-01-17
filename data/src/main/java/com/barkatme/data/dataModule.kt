package com.barkatme.data


import android.app.Application
import androidx.room.Room
import com.barkatme.data.api.giphy.GiphyApiImpl
import com.barkatme.data.api.placeholder.PlaceholderApiImpl
import com.barkatme.data.api.placeholder.PlaceholderFlowApiImpl
import com.barkatme.data.repository.GiphyLocalPagedRepository
import com.barkatme.data.repository.GiphyLocalRepositoryImpl
import com.barkatme.data.repository.GiphyRepositoryImpl
import com.barkatme.data.repository.room.AppDatabase
import com.barkatme.demo.domain.api.GiphyApi
import com.barkatme.demo.domain.api.PlaceholderApi
import com.barkatme.demo.domain.api.PlaceholderFlowApi
import com.barkatme.demo.domain.domainModule
import com.barkatme.demo.domain.repository.GiphyLocalRepository
import com.barkatme.demo.domain.repository.GiphyRepository
import org.koin.android.ext.koin.androidContext
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

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()
    }

    single { get<AppDatabase>().gifDao() }

    single<GiphyRepository> { GiphyRepositoryImpl(get(), get()) }

    single<GiphyLocalRepository> { GiphyLocalRepositoryImpl(get()) }

    single<GiphyLocalPagedRepository> { GiphyLocalPagedRepository(get()) }
}

val startDataKoin = { application: Application ->
    startKoin {
        androidContext(application)
        modules(domainModule, dataModule)
    }
}