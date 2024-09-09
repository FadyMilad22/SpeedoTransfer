package com.example.speedotransfer.data.repository

import SignInRequest
import SignInResponse
import com.example.speedotransfer.model.CustomerResponse

interface SignInRepo {

    suspend fun signIn(signInRequest: SignInRequest): SignInResponse
    suspend fun getCustomerByEmail(email: String): CustomerResponse
}