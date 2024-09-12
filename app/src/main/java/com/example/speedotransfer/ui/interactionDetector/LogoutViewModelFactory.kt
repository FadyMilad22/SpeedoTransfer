package com.example.speedotransfer.ui.interactionDetector

import EditProfileScreenViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.repository.logout.LogoutRepo

class LogoutViewModelFactory(private val logoutRepo: LogoutRepo) :
    ViewModelProvider.Factory
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(LogoutViewModel::class.java)) {
                LogoutViewModel(logoutRepo) as T
            } else {
                throw IllegalArgumentException("Logout ViewModel class not found")
            }
        }

    }

