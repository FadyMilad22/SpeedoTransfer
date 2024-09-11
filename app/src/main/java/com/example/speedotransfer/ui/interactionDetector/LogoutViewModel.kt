package com.example.speedotransfer.ui.interactionDetector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.repository.logout.LogoutRepo
import com.example.speedotransfer.model.LogoutResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogoutViewModel(private val logoutRepo: LogoutRepo) : ViewModel() {

    private val _logoutState = MutableStateFlow<LogoutResponse?>(null)
    val logoutState: StateFlow<LogoutResponse?> = _logoutState

    // Loading states
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Error handling state
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // FIX: Method to reset the state back to null after logout
    fun setStateBacktoNull() {
        _logoutState.value = null
    }

    // Method to log out the user
    fun logout(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = logoutRepo.logout(token)
                _logoutState.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Error during logout: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}