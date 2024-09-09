package com.example.speedotransfer.ui.screens.tansfer.homeScreen

import HomeViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.repository.SignInRepo
import com.example.speedotransfer.data.repository.TransferRepo


class HomeViewModelFactory(private val homeRepo: TransferRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(homeRepo) as T
        }else{
            throw IllegalArgumentException("HomeViewModel class not found")
        }
    }
}