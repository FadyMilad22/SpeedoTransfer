package com.example.speedotransfer.data.repository

import SignInRequest
import SignInResponse
import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.CustomerResponse

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

    // Repo function to get customer details by email
    override suspend fun getCustomerByEmail(email: String): CustomerResponse {
        // Mocked response for testing purposes
        val mockResponse = CustomerResponse(
            id = 1,
            name = "John Doe",
            email = email,
            phoneNumber = "123-456-7890",
            createdAt = "2024-01-01T12:00:00Z",
            updatedAt = "2024-09-08T19:13:48.927Z"
        )

        // Uncomment this when the API is live
        // return apiClient.getCustomerByEmail(email)

        return mockResponse // Using mock response until the API is live
    }

}