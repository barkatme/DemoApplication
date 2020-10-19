package com.barkatme.demo

import android.app.Application
import androidx.room.Room
import com.barkatme.data.dataModule
import com.barkatme.demo.data.GiphyLocalRepositoryImpl
import com.barkatme.demo.data.room.AppDatabase
import com.barkatme.demo.domain.domainModule
import com.barkatme.demo.domain.repository.GiphyLocalRepository
import com.barkatme.demo.ui.main.coroutines.channel.CoroutinesChannelViewModel
import com.barkatme.demo.ui.main.coroutines.flow.CoroutinesFlowViewModel
import com.barkatme.demo.ui.main.coroutines.menu.CoroutinesMenuViewModel
import com.barkatme.demo.ui.main.jetpack.camerax.JetpackCameraXViewModel
import com.barkatme.demo.ui.main.jetpack.menu.JetpackMenuViewModel
import com.barkatme.demo.ui.main.jetpack.paging.JetpackPagingViewModel
import com.barkatme.demo.ui.main.room.giphy.RoomGiphyViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val presentationModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()
    }

    single { get<AppDatabase>().gifDao() }

    single<GiphyLocalRepository> { GiphyLocalRepositoryImpl(get()) }

    //coroutines
    viewModel { CoroutinesMenuViewModel() }
    viewModel { CoroutinesFlowViewModel(get()) }
    viewModel { CoroutinesChannelViewModel(get()) }

    //jetpack
    viewModel { JetpackMenuViewModel() }
    viewModel { JetpackCameraXViewModel() }
    viewModel { JetpackPagingViewModel(get()) }

    //room
    viewModel { RoomGiphyViewModel(get(), get()) }
    single { Channel<Long>() }
}

@ExperimentalCoroutinesApi
@FlowPreview
val startPresentationKoin = { application: Application ->
    startKoin {
        androidContext(application)
        modules(dataModule, domainModule, presentationModule)
        logger(AndroidLogger())
    }
}