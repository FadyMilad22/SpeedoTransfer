package com.example.speedotransfer.ui.screens.tansfer.transferConfirmationScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.repository.transfer.TransferRepo

class TransferViewModelFactory(private val transferRepo: TransferRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TransferViewModel::class.java)) {
            TransferViewModel(transferRepo) as T
        }else{
            throw IllegalArgumentException("TransferViewModel class not found")
        }
    }
}