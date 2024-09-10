package com.example.speedotransfer.data.repository.singUp

import RegisterCustomerRequest
import RegisterCustomerResponse
import com.example.speedotransfer.data.network.RemoteDataSource

class SignUpRepoImpl(remoteDataSource: RemoteDataSource): SignUpRepo {
    // Repo function to register a new customer using repository pattern
    override suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse {
        // Mocked object for testing purposes
        val mockResponse = RegisterCustomerResponse(
            id = 1,
            name = "John Doe",
            email = "johndoe@example.com",
            createdAt = "2024-09-04T02:47:40.62069",
            updatedAt = "2024-09-04T02:47:40.62069"
        )

        // Uncomment this when the API is live
        // return remoteDataSource.registerCustomer(registerRequest)

        return mockResponse // Using mock object until the API is live
    }
}