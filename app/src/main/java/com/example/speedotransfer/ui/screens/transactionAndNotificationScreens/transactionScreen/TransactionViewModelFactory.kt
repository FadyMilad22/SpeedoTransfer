package com.example.speedotransfer.ui.screens.transactionAndNotificationScreens.transactionScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.repository.TransactionRepo

class TransactionViewModelFactory(private val transactionRepo: TransactionRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            TransactionViewModel(transactionRepo) as T
        }else{
            throw IllegalArgumentException("Transaction ViewModel class not found")
        }
    }
}