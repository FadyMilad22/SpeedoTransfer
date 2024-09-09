package com.example.speedotransfer.ui.screens.profile.EditProfileScreen

import EditProfileScreenViewModel
import SignInViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.repository.EditProfileRepo

class EditProfileScreenViewModelFactory (private val editProfileRepo: EditProfileRepo):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(EditProfileScreenViewModel::class.java)) {
            EditProfileScreenViewModel(editProfileRepo) as T
        }else{
            throw IllegalArgumentException("Edit profile ViewModel class not found")
        }
    }
}