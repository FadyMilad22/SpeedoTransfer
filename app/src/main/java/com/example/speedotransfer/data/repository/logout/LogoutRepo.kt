package com.example.speedotransfer.data.repository.logout

import com.example.speedotransfer.model.LogoutResponse

interface LogoutRepo {
    suspend fun logout(token: String): LogoutResponse

}