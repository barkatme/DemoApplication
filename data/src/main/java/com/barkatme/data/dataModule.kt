package com.barkatme.data

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.barkatme.data.api.demo.DemoApiImpl
import com.barkatme.data.api.giphy.GiphyApiImpl
import com.barkatme.data.api.placeholder.PlaceholderApiImpl
import com.barkatme.data.api.placeholder.PlaceholderFlowApiImpl
import com.barkatme.data.repository.AppDatabase
import com.barkatme.data.repository.demo.ChatRepositoryImpl
import com.barkatme.data.repository.demo.TokenRepositoryImpl
import com.barkatme.data.repository.firstpdm.giphy.GiphyLocalPagedRepository
import com.barkatme.data.repository.firstpdm.giphy.GiphyLocalRepositoryImpl
import com.barkatme.data.repository.firstpdm.giphy.GiphyRepositoryImpl
import com.barkatme.demo.domain.api.DemoApi
import com.barkatme.demo.domain.api.GiphyApi
import com.barkatme.demo.domain.api.PlaceholderApi
import com.barkatme.demo.domain.api.PlaceholderFlowApi
import com.barkatme.demo.domain.domainModule
import com.barkatme.demo.domain.repository.ChatRepository
import com.barkatme.demo.domain.repository.TokenRepository
import com.barkatme.demo.domain.repository.giphy.GiphyLocalRepository
import com.barkatme.demo.domain.repository.giphy.GiphyRepository
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module


val dataModule = module(override = true) {

    factory {
        Json {
            ignoreUnknownKeys = false
            prettyPrint = true
        }
    }

    single<DemoApi> { DemoApiImpl(get(), get()) }

    factory {
        androidApplication().getSharedPreferences(
            "shared_preferences",
            Context.MODE_PRIVATE
        )
    }
    single<ChatRepository> { ChatRepositoryImpl(get()) }
    single<TokenRepository> { TokenRepositoryImpl(get()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()
    }

    //first pdm
    single<PlaceholderApi> { PlaceholderApiImpl() }
    single<PlaceholderFlowApi> { PlaceholderFlowApiImpl() }
    single<GiphyApi> { GiphyApiImpl(get()) }
    single { get<AppDatabase>().gifDao() }
    single<GiphyRepository> { GiphyRepositoryImpl(get(), get()) }
    single<GiphyLocalRepository> { GiphyLocalRepositoryImpl(get()) }
    single { GiphyLocalPagedRepository(get()) }
}

val startDataKoin = { application: Application ->
    startKoin {
        androidContext(application)
        modules(domainModule, dataModule)
    }
}