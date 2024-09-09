package com.example.speedotransfer.ui.screens.authentication.signInScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.repository.SignInRepo


class SignInViewModelFactory(private val signInRepo: SignInRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            SignInViewModel(signInRepo) as T
        }else{
            throw IllegalArgumentException("Sign in ViewModel class not found")
        }
    }
}