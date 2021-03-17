package com.barkatme.demo.domain

import com.barkatme.demo.domain.usecase.demo.auth.SignInUseCase
import com.barkatme.demo.domain.usecase.demo.auth.SignOutUseCase
import com.barkatme.demo.domain.usecase.demo.auth.SignUpUseCase
import com.barkatme.demo.domain.usecase.demo.chat.MessagesFlowUseCase
import com.barkatme.demo.domain.usecase.demo.chat.NewMessageUseCase
import com.barkatme.demo.domain.usecase.demo.user.GetCurrentUserUseCase
import com.barkatme.demo.domain.usecase.flow.GetTodoFlowUseCase
import com.barkatme.demo.domain.usecase.giphy.SearchGifsUseCase
import com.barkatme.demo.domain.usecase.giphy.TrendingGifsUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.module

val domainModule = module {

    single { MessagesFlowUseCase(get()) }
    single { NewMessageUseCase(get()) }

    single { SignInUseCase(get(), get()) }
    single { SignUpUseCase(get(), get()) }
    single { SignOutUseCase(get(), get()) }
    single { GetCurrentUserUseCase(get(), get()) }

    single { GetTodoFlowUseCase(get()) }
    single { TrendingGifsUseCase(get()) }
    single { SearchGifsUseCase(get()) }
}

val startDomainKoin = {
    startKoin {
        modules(domainModule)
    }
}