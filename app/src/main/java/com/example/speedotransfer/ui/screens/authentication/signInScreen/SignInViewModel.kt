package com.example.speedotransfer.ui.screens.authentication.signInScreen

import SignInRequest
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.repository.signIn.SignInRepo
import com.example.speedotransfer.model.CustomerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val signInRepo: SignInRepo, private val sharedPreferences: SharedPreferences ) : ViewModel() {


    // StateFlow for handling the current state of the screen
    private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
    val signInState: StateFlow<SignInState> = _signInState

    // StateFlow for holding customer data
    private val _customerState = MutableStateFlow<CustomerResponse?>(null)
    val customerState: StateFlow<CustomerResponse?> = _customerState


    // MutableStateFlow for email and password
    var email = MutableStateFlow("")
    var password = MutableStateFlow("")
    var isPasswordValid = MutableStateFlow(true)

    // Regex pattern for password validation (6 characters, one capital letter, one small letter, and one special character)
    private val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{6,}$".toRegex()

    // Method to update the email value
    fun onEmailChange(newEmail: String) {
        email.value = newEmail
    }

    // Method to update the password value and validate it
    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
        isPasswordValid.value = isPasswordValid(newPassword)
    }

    // Password validation function
    private fun isPasswordValid(password: String): Boolean {
        return passwordPattern.matches(password)
    }

    // Function to log in the user
//    fun logIn() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                _signInState.value = SignInState.Loading
//                val request = SignInRequest(email.value, password.value)
//                val response = signInRepo.signIn(request)
//                _signInState.value = SignInState.Success("Login successful: ${response.message}")
//            } catch (e: Exception) {
//                _signInState.value = SignInState.Error("Login failed: ${e.message}")
//            }
//        }
//    }
    // Function to log in the user
    fun logIn() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _signInState.value = SignInState.Loading
                val request = SignInRequest(email.value, password.value)
                val response = signInRepo.signIn(request)

                sharedPreferences.edit().apply {
                    putString("auth_token", response.token)
                    putString("token_type", response.tokenType)
                    apply() // Apply changes to SharedPreferences
                }
                // If login is successful, fetch customer details by email
                fetchCustomerByEmail(email.value)
                _signInState.value = SignInState.Success("Login successful: ${response.message}")
            } catch (e: Exception) {
                _signInState.value = SignInState.Error("Login failed: ${e.message}")
            }
        }
    }




    private fun fetchCustomerByEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val customer = signInRepo.getCustomerByEmail(email)
                _customerState.value = customer // Store customer details
                saveCustomerDataToPreferences(customer)
            } catch (e: Exception) {
                _signInState.value = SignInState.Error("Failed to fetch customer details: ${e.message}")
            }
        }
    }
    private fun saveCustomerDataToPreferences(customer: CustomerResponse) {
        sharedPreferences.edit().apply {
            // Save customer data
            putString("customer_birthDate", customer.birthDate)
            putString("customer_createdAt", customer.createdAt)
            putString("customer_email", customer.email)
            putString("customer_gender", customer.gender)
            putInt("customer_id", customer.id)
            putString("customer_name", customer.name)
            putString("customer_phoneNumber", customer.phoneNumber)
            putString("customer_updatedAt", customer.updatedAt)
            putString("customer_username", customer.username)

            // Save account[0] data (assuming we only handle the first account)
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


            apply() // Apply changes to SharedPreferences
        }
    }

}

// com.example.speedotransfer.ui.screens.authentication.signInScreen.SignInState to manage different states
sealed class SignInState {
    object Idle : SignInState()
    object Loading : SignInState()
    data class Success(val message: String) : SignInState()
    data class Error(val message: String) : SignInState()
}


