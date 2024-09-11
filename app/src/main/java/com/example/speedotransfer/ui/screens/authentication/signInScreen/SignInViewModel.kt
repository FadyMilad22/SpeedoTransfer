package com.example.speedotransfer.ui.screens.authentication.signInScreen

import SignInRequest
import SignInResponse
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.repository.signIn.SignInRepo
import com.example.speedotransfer.model.CustomerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInRepo: SignInRepo,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
    val signInState: StateFlow<SignInState> = _signInState

    private val _customerState = MutableStateFlow<CustomerResponse?>(null)
    val customerState: StateFlow<CustomerResponse?> = _customerState

    private val _response = MutableStateFlow<SignInResponse?>(null)
    val response: StateFlow<SignInResponse?> = _response

    var email = MutableStateFlow("")
    var password = MutableStateFlow("")
    var isPasswordValid = MutableStateFlow(true)

    private val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{6,}$".toRegex()

    fun onEmailChange(newEmail: String) {
        email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
        isPasswordValid.value = isPasswordValid(newPassword)
    }

    private fun isPasswordValid(password: String): Boolean {
        return passwordPattern.matches(password)
    }

    fun logIn() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _signInState.value = SignInState.Loading
                val request = SignInRequest(email.value, password.value)

                _response.value = signInRepo.signIn(request)

                sharedPreferences.edit().apply {
                    putString("auth_token", _response.value?.token)
                    putString("token_type", _response.value?.tokenType)
                    apply() // Apply changes to SharedPreferences
                }

                _signInState.value =
                    SignInState.Success("Login successful: ${response.value?.message}")


            } catch (e: Exception) {
                _signInState.value = SignInState.Error("Login failed: ${e.message}")
            }
        }
    }

    fun fetchCustomerByEmail(authToken: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                _customerState.value =
                    signInRepo.getCustomerByEmail(authToken, email)// Store customer details
            } catch (e: Exception) {
                _signInState.value =
                    SignInState.Error("Failed to fetch customer details: ${e.message}")
            }
        }
    }

    fun saveCustomerDataToPreferences(customer: CustomerResponse) {
        sharedPreferences.edit().apply {
            putString("customer_birthDate", customer.birthDate)
            putString("customer_createdAt", customer.createdAt)
            putString("customer_email", customer.email)
            putString("customer_gender", customer.gender)
            putInt("customer_id", customer.id)
            putString("customer_name", customer.name)
            putString("customer_phoneNumber", customer.phoneNumber)
            putString("customer_updatedAt", customer.updatedAt)
            putString("customer_username", customer.username)

            val account = customer.accounts[0]
            putString("account_description", account.accountDescription)
            putString("account_name", account.accountName)
            putString("account_number", account.accountNumber)
            putString("account_type", account.accountType)
            putBoolean("account_active", account.active)
            putInt("account_balance", account.balance)
            putString("account_createdAt", account.createdAt)
            putString("account_currency", account.currency)
            putInt("account_id", account.id)
            putString("account_updatedAt", account.updatedAt)

            apply()
        }
    }

}

sealed class SignInState {
    object Idle : SignInState()
    object Loading : SignInState()

    data class Success(val message: String) : SignInState()
    data class Error(val message: String) : SignInState()
}


