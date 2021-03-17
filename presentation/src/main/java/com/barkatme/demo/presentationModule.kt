package com.barkatme.demo

import android.app.Application
import com.barkatme.data.dataModule
import com.barkatme.demo.domain.domainModule
import com.barkatme.demo.ui.main.pdm1.coroutines.channel.CoroutinesChannelViewModel
import com.barkatme.demo.ui.main.pdm1.coroutines.flow.CoroutinesFlowViewModel
import com.barkatme.demo.ui.main.pdm1.coroutines.menu.CoroutinesMenuViewModel
import com.barkatme.demo.ui.main.pdm1.jetpack.camerax.JetpackCameraXViewModel
import com.barkatme.demo.ui.main.pdm1.jetpack.menu.JetpackMenuViewModel
import com.barkatme.demo.ui.main.pdm1.jetpack.paging.JetpackPagingViewModel
import com.barkatme.demo.ui.main.pdm1.room.giphy.RoomGiphyViewModel
import com.barkatme.demo.ui.main.pdm2.auth.AuthViewModel
import com.barkatme.demo.ui.main.pdm2.chat.ChatViewModel
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

    viewModel { AuthViewModel(get(), get()) }

    //coroutines
    viewModel { ChatViewModel(get(), get()) }
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