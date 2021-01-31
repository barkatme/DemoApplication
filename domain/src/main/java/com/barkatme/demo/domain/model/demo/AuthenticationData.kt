package com.barkatme.demo.domain.model.demo

class AuthenticationData private constructor() {

    var token: Token? = null

    companion object {
        private var instance: AuthenticationData? = null

        @JvmStatic
        fun getInstance(): AuthenticationData {
            if (instance == null) {
                instance = AuthenticationData()
            }
            return instance!!
        }
    }
}