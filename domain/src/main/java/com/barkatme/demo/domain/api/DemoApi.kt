package com.barkatme.demo.domain.api

import com.barkatme.demo.domain.model.demo.Credentials
import com.barkatme.demo.domain.model.demo.Token

interface DemoApi {

    object URL {
        private const val BASE = "https://barkatme-demo.herokuapp.com/"

        const val signIn = BASE + "auth/sign_in"
        const val signUp = BASE + "auth/sign_up"
        const val signOut = BASE + "auth/sign_out"

        const val currentUser = BASE + "user"
    }

    companion object{
        const val USER_ID_PARAM = "userId"
    }

    suspend fun signIn(credentials: Credentials): Token
    suspend fun signUp(credentials: Credentials): Token
    suspend fun signOut(): Token

    suspend fun getCurrentUser(): String
}