package com.example.speedotransfer.data.repository.signIn

import SignInRequest
import SignInResponse
import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.Account
import com.example.speedotransfer.model.CustomerResponse

class SignInRepoImpl(remoteDataSource: RemoteDataSource): SignInRepo {

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
        // Mock Account Object
        val mockAccount = Account(
            accountDescription = "Personal Savings Account",
            accountName = "John's Savings",
            accountNumber = "1234567890",
            accountType = "Savings",
            active = true,
            balance = 5000,
            createdAt = "2023-01-01T12:00:00Z",
            currency = "USD",
            id = 1,
            updatedAt = "2023-08-01T12:00:00Z"
        )

// Mock CustomerResponse Object
        val mockResponse = CustomerResponse(
            accounts = listOf(mockAccount),  // A list of account objects
            birthDate = "1990-01-01",
            createdAt = "2022-05-01T12:00:00Z",
            email = "john.doe@example.com",
            gender = "Male",
            id = 123,
            name = "John Doe",
            phoneNumber = "+1234567890",
            updatedAt = "2023-08-01T12:00:00Z",
            username = "johndoe"
        )

        // Uncomment this when the API is live
        // return apiClient.getCustomerByEmail(email)

        return mockResponse // Using mock response until the API is live
    }

}