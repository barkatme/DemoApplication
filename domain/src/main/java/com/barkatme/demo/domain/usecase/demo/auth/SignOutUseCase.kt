package com.barkatme.demo.domain.usecase.demo.auth

import com.barkatme.demo.domain.api.DemoApi
import com.barkatme.demo.domain.model.demo.Token
import com.barkatme.demo.domain.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignOutUseCase(
    private val demoApi: DemoApi,
    private val tokenRepository: TokenRepository
) {
    suspend fun signOut(): Token = withContext(Dispatchers.IO) {
        demoApi.signOut().also { tokenRepository.saveToken(it) }
    }
}