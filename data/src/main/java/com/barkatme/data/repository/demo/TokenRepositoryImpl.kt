package com.barkatme.data.repository.demo

import android.content.SharedPreferences
import com.barkatme.demo.domain.model.demo.Token
import com.barkatme.demo.domain.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TokenRepositoryImpl(
    private val preferences: SharedPreferences
) : TokenRepository {

    override suspend fun saveToken(token: Token): Unit = withContext(Dispatchers.IO) {
        preferences.edit().putString(TokenRepository.TOKEN_KEY, token.token).apply()
    }

    override suspend fun getToken(): Token? = withContext(Dispatchers.IO) {
        preferences.getString(TokenRepository.TOKEN_KEY, null)?.let { Token(it) }
    }

    override suspend fun requireToken(): Token = withContext(Dispatchers.IO) {
        preferences.getString(TokenRepository.TOKEN_KEY, null)?.let { Token(it) }
            ?: throw RuntimeException("token not found")
    }
}