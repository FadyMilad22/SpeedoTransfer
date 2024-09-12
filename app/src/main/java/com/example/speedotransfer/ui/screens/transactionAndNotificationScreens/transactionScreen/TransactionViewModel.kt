package com.example.speedotransfer.ui.screens.transactionAndNotificationScreens.transactionScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.repository.transaction.TransactionRepo
import com.example.speedotransfer.model.TransactionResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionViewModel(private val transferRepo: TransactionRepo):ViewModel() {




    // State for storing transaction history
    private val _transactionHistory = MutableStateFlow<List<TransactionResponse>>(emptyList())
    val transactionHistory: StateFlow<List<TransactionResponse>> = _transactionHistory

    // State for storing transaction details by ID
    private val _transactionDetails = MutableStateFlow<TransactionResponse?>(null)
    val transactionDetails: StateFlow<TransactionResponse?> = _transactionDetails


    // Loading states
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Error handling state
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Fetch transaction history
    fun fetchTransactionHistory(startDate: String, endDate: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val history = transferRepo.getTransactionHistory("$endDate $startDate")
Log.d("API TransactionUI","inside : $endDate $startDate")
                _transactionHistory.value = history.transactions
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching transaction history: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }


    // Fetch transaction details by ID
    fun getTransactionById(token :String,transactionId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val transaction = transferRepo.getTransactionById(token,transactionId)
                Log.d("API Notify Details VM",token)
                _transactionDetails.value = transaction
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching transaction details: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }



}