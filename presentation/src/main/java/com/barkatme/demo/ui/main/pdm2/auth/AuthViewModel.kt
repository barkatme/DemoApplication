package com.barkatme.demo.ui.main.pdm2.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barkatme.demo.domain.model.demo.AuthenticationData
import com.barkatme.demo.domain.model.demo.Token
import com.barkatme.demo.domain.usecase.demo.auth.SignInUseCase
import com.barkatme.demo.domain.usecase.demo.user.GetCurrentUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    companion object {
        const val TAG = "AuthViewModel"
    }

    var onAuthCompleted: suspend (token: Token?) -> Unit = {}

    fun signIn() {
        viewModelScope.launch {
            signInUseCase.signIn("123@qwe", "123qwe")
            Log.d(TAG, "currentUser = ${getCurrentUserUseCase.getUser()}")
            val token = AuthenticationData.getInstance().token
            withContext(Dispatchers.Main) {
                onAuthCompleted.invoke(token)
            }
        }
    }
}