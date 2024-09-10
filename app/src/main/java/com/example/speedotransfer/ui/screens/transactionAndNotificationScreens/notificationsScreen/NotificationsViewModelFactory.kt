package com.example.speedotransfer.ui.screens.transactionAndNotificationScreens.notificationsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.repository.transaction.TransactionRepo

class NotificationsViewModelFactory(private val transactionRepo: TransactionRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NotificationsViewModel::class.java)) {
            NotificationsViewModel(transactionRepo) as T
        }else{
            throw IllegalArgumentException("Notification ViewModel class not found")
        }
    }
}