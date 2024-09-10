package com.example.speedotransfer.ui.screens.more.favourite

import FavViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.repository.favourite.FavRepo
import com.example.speedotransfer.data.repository.logout.LogoutRepo
import com.example.speedotransfer.ui.screens.more.logout.LogoutViewModel

class FavViewModelFactory (private val favRepo: FavRepo) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FavViewModel::class.java)) {
            FavViewModel(favRepo) as T
        } else {
            throw IllegalArgumentException("Logout ViewModel class not found")
        }
    }

}