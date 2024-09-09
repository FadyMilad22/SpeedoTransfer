package com.example.speedotransfer.data.repository

import SignInRequest
import SignInResponse

interface SignInRepo {

    suspend fun signIn(signInRequest: SignInRequest): SignInResponse

}