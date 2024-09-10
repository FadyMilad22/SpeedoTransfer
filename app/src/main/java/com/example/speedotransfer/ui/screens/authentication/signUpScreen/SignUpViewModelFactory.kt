package com.example.speedotransfer.ui.screens.authentication.signUpScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.repository.singUp.SignUpRepo

class SignUpViewModelFactory(private val signUpRepo: SignUpRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            SignUpViewModel(signUpRepo) as T
        }else{
            throw IllegalArgumentException("Sign up ViewModel class not found")
        }
    }
}