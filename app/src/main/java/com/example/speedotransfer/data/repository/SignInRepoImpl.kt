package com.example.speedotransfer.data.repository

import LoginRequest
import LoginResponse

class SignInRepoImpl:SignInRepo {

    // Repo function to log in using repository pattern
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        // Mocked object for testing purposes
        val mockResponse = LoginResponse(
            message = "Login Successful",
            token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2RhQGdtYWlsLmNvbSIsImlhdCI6MTcyNTQwNzQ2OSwiZXhwIjoxNzI1NDkzODY5fQ.i1qi6vdOjMElgd4mNc2MAnkbhX9sLihmUZLbVTRaLyk",
            tokenType = "Bearer",
            status = "ACCEPTED"
        )
        // Uncomment this when the API is live
        // return remoteDataSource.login(loginRequest)

        return mockResponse // Using mock object until the API is live
    }

}