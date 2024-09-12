package com.example.speedotransfer.ui.screens.authentication.signUpScreen

import RegisterCustomerRequest
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.repository.singUp.SignUpRepo
import com.example.speedotransfer.model.RegisterCustomerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val signUpRepo: SignUpRepo) : ViewModel() {

    val fullName = MutableStateFlow("")
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")
    val isPasswordValid = MutableStateFlow(true)
    val isConfirmPasswordValid = MutableStateFlow(true)
    val country = MutableStateFlow("")
    val birthDate = MutableStateFlow("")

    val response = MutableStateFlow<RegisterCustomerResponse?>(null)

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return email.matches(Regex(emailRegex))
    }

    private val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{6,}$".toRegex()

    fun onFullNameChange(newFullName: String) {
        fullName.value = newFullName
    }

    fun onEmailChange(newEmail: String) {
        email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
        isPasswordValid.value = passwordPattern.matches(newPassword)
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        confirmPassword.value = newConfirmPassword
        isConfirmPasswordValid.value = newConfirmPassword == password.value
    }

    fun onCountryChange(newCountry: String) {  // Added this function to handle country change
        country.value = newCountry
    }

    fun onBirthDateChange(newBirthDate: String) {  // Added this function to handle birthDate change
        birthDate.value = newBirthDate
    }


    fun registerCustomer(context: Context) {
        //  if (isFormValid()) {
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

                Log.d("API Test SignUP", "createdAt: $request")

                response.value = signUpRepo.registerCustomer(request)
            } catch (e: Exception) {


                Log.d("API Test SignUP", "catch ")
                ////    Log.d("API Test SignUP"," ${e.message}\n ${e.localizedMessage}")
                //   }
            }
        }
    }

    fun isFormValid(): Boolean {
        return fullName.value.isNotBlank() &&
                email.value.isNotBlank() &&
                isPasswordValid.value &&
                isConfirmPasswordValid.value && confirmPassword.value.isNotBlank()
                && isConfirmPasswordValid.value &&
                confirmPassword.value == password.value
//                country.value.isNotBlank() &&
//                birthDate.value.isNotBlank()
    }
}
