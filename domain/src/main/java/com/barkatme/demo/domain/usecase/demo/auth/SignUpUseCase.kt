package com.barkatme.demo.domain.usecase.demo.auth

import com.barkatme.demo.domain.api.DemoApi
import com.barkatme.demo.domain.model.demo.Credentials
import com.barkatme.demo.domain.model.demo.Token
import com.barkatme.demo.domain.repository.TokenRepository

class SignUpUseCase(
    private val demoApi: DemoApi,
    private val tokenRepository: TokenRepository
) {
    suspend fun signUp(email: String, password: String): Token {
        val token = demoApi.signUp(Credentials(email, password))
        tokenRepository.saveToken(token)
        return token
    }
}