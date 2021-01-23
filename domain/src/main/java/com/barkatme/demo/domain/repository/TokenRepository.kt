package com.barkatme.demo.domain.repository

import com.barkatme.demo.domain.model.demo.Token

interface TokenRepository {
    companion object {
        const val TOKEN_KEY = "token_key"
    }

    suspend fun saveToken(token: Token)
    suspend fun getToken(): Token?
    suspend fun requireToken(): Token

}