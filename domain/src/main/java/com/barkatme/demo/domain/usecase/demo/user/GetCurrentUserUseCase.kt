package com.barkatme.demo.domain.usecase.demo.user

import com.barkatme.demo.domain.api.DemoApi
import com.barkatme.demo.domain.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCurrentUserUseCase(
    private val demoApi: DemoApi,
    private val tokenRepository: TokenRepository
) {
    suspend fun getUser(): String = withContext(Dispatchers.IO) {
        demoApi.getCurrentUser()
    }
}