package com.example.speedotransfer.ui.screens.more.logout

import EditProfileScreenViewModel
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.repository.logout.LogoutRepo

class LogoutViewModelFactory(private val logoutRepo: LogoutRepo,    private val sharedPreferences: SharedPreferences
) :
    ViewModelProvider.Factory
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(LogoutViewModel::class.java)) {
                LogoutViewModel(logoutRepo, sharedPreferences) as T
            } else {
                throw IllegalArgumentException("Logout ViewModel class not found")
            }
        }

    }

