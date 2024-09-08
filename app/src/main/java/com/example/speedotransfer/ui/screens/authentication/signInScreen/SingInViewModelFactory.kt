package com.example.speedotransfer.ui.screens.authentication.signInScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.repository.SignInRepo


class SignInViewModelFactory(private val loginRepo: SignInRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            SignInViewModel(loginRepo) as T
        }else{
            throw IllegalArgumentException("settingsViewModel class not found")
        }
    }
}