package com.example.speedotransfer.ui.screens.authentication.signUpScreen

import RegisterCustomerRequest
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.repository.singUp.SignUpRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val signUpRepo: SignUpRepo) : ViewModel() {

    // StateFlows for the form data
    val fullName = MutableStateFlow("")
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")
    val isPasswordValid = MutableStateFlow(true)
    val isConfirmPasswordValid = MutableStateFlow(true)
    // Add country and birthDate StateFlow
    val country = MutableStateFlow("")  // Added country
    val birthDate = MutableStateFlow("")  // Added birthDate

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return email.matches(Regex(emailRegex))
    }
    // Regex pattern for password validation
    private val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{6,}$".toRegex()

    // Function to update the full name
    fun onFullNameChange(newFullName: String) {
        fullName.value = newFullName
    }

    // Function to update the email
    fun onEmailChange(newEmail: String) {
        email.value = newEmail
    }

    // Function to update the password and validate it
    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
        isPasswordValid.value = passwordPattern.matches(newPassword)
    }

    // Function to update the confirm password and validate if it matches the password
    fun onConfirmPasswordChange(newConfirmPassword: String) {
        confirmPassword.value = newConfirmPassword
        isConfirmPasswordValid.value = newConfirmPassword == password.value
    }

    // Function to update country
    fun onCountryChange(newCountry: String) {  // Added this function to handle country change
        country.value = newCountry
    }

    // Function to update birthDate
    fun onBirthDateChange(newBirthDate: String) {  // Added this function to handle birthDate change
        birthDate.value = newBirthDate
    }



    // Function to handle the registerCustomer action
    fun registerCustomer() {
        if (isFormValid()) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val request = RegisterCustomerRequest(
                        name = fullName.value,
                        email = email.value,
                        password = password.value,
                        confirmPassword = confirmPassword.value,
                        country = country.value,  // Pass country in the request
                        birthDate = birthDate.value  // Pass birthDate in the request
                    )
                    val response =signUpRepo.registerCustomer(request)
Log.d("sign up","createdAt: ${response.createdAt}")                  // Handle success (you can update the state here)
                } catch (e: Exception) {
                    // Handle error (you can update the state here)
                }
            }
        }
    }

    // Check if the form is valid
    fun isFormValid(): Boolean {
        return fullName.value.isNotBlank() &&
                email.value.isNotBlank() &&
                isPasswordValid.value &&
                isConfirmPasswordValid.value && confirmPassword.value.isNotBlank()
                && isConfirmPasswordValid.value &&
                confirmPassword.value==password.value
//                country.value.isNotBlank() &&
//                birthDate.value.isNotBlank()
    }
}
