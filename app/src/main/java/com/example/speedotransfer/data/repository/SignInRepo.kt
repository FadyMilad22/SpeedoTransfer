package com.example.speedotransfer.data.repository

import LoginRequest
import LoginResponse

interface SignInRepo {

    suspend fun login(loginRequest: LoginRequest): LoginResponse

}