package com.example.speedotransfer.data.repository

import SignInRequest
import SignInResponse
import com.example.speedotransfer.data.network.RemoteDataSource

class SignInRepoImpl(remoteDataSource: RemoteDataSource):SignInRepo {

    // Repo function to log in using repository pattern
    override suspend fun signIn(signInRequest: SignInRequest): SignInResponse {
        // Mocked object for testing purposes
        val mockResponse = SignInResponse(
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