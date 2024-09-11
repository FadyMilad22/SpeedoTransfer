package com.example.speedotransfer.ui.screens.transactionAndNotificationScreens.notificationsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.repository.transaction.TransactionRepo
import com.example.speedotransfer.model.TransactionResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotificationsViewModel(private val transferRepo: TransactionRepo):ViewModel() {




    // State for storing transaction history
    private val _notificationHistory = MutableStateFlow<List<TransactionResponse>>(emptyList())
    val notificationHistory: StateFlow<List<TransactionResponse>> = _notificationHistory


    // Loading states
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Error handling state
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Fetch transaction history
    fun fetchTransactionHistory(accountId: Long, startDate: String, endDate: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val history = transferRepo.getTransactionHistory()
                _notificationHistory.value = history
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching notification history: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }






}