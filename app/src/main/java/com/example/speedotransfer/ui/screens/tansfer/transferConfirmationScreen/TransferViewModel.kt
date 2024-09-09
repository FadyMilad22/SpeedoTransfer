package com.example.speedotransfer.ui.screens.tansfer.transferConfirmationScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.repository.TransferRepo
import com.example.speedotransfer.model.Transfer
import com.example.speedotransfer.model.TransferRequest
import com.example.speedotransfer.model.TransferResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransferViewModel(private val transferRepo: TransferRepo):ViewModel() {




    // State for storing transfer result
    private val _transferResult = MutableStateFlow<TransferResponse?>(null)
    val transferResult: StateFlow<TransferResponse?> = _transferResult

    // Loading states
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Error handling state
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage





    // Transfer money between accounts
    fun transferMoney(transferRequest: TransferRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val transferResponse = transferRepo.transferMoney(transferRequest)
                Log.d("TagTransfer", "Transfer response: ${transferResponse.status}")
                _transferResult.value = transferResponse
            } catch (e: Exception) {
                _errorMessage.value = "Error during transfer: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

}