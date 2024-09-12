package com.example.speedotransfer.ui.screens.more.logout

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.repository.logout.LogoutRepo
import com.example.speedotransfer.model.LogoutResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogoutViewModel (private val logoutRepo: LogoutRepo ,     private val sharedPreferences: SharedPreferences
) : ViewModel(){
    private val _logoutState = MutableStateFlow<LogoutResponse?>(null)
    val logoutState: StateFlow<LogoutResponse?> = _logoutState
    // Loading states
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Error handling state
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage



    // Method to update profile
    fun logout(token :String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = logoutRepo.logout(token)
                _logoutState.value = response
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

            } catch (e: Exception) {
                _errorMessage.value = "Error during editing logout: ${e.message}"}
            finally {
                _isLoading.value = false
            }
        }
    }
}



