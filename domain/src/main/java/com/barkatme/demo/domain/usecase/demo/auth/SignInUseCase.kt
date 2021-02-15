package com.barkatme.demo.domain.usecase.demo.auth

import com.barkatme.demo.domain.api.DemoApi
import com.barkatme.demo.domain.model.demo.AuthenticationData
import com.barkatme.demo.domain.model.demo.Credentials
import com.barkatme.demo.domain.model.demo.Token
import com.barkatme.demo.domain.repository.TokenRepository
import com.barkatme.demo.domain.usecase.demo.notifier.Notifier

class SignInUseCase(
    private val demoApi: DemoApi,
    private val tokenRepository: TokenRepository,
    private val notifier: Notifier
) {
    suspend fun signIn(email: String, password: String): Token {
        val token = demoApi.signIn(Credentials(email, password))
        notifier.notify("signed in with token $token")
        tokenRepository.saveToken(token)
        AuthenticationData.getInstance().token = token
        return token
    }
}