package com.barkatme.demo.ui.main.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barkatme.demo.domain.usecase.demo.auth.SignInUseCase
import com.barkatme.demo.domain.usecase.demo.user.GetCurrentUserUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    fun signIn() {
        viewModelScope.launch {
            signInUseCase.signIn("123@qwe", "123qwe")
            Log.d("AuthViewModel", "currentUser = ${getCurrentUserUseCase.getUser()}")
        }
    }
}