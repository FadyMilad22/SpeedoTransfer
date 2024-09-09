package com.example.speedotransfer.data.repository

import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.HttpStatusCode
import com.example.speedotransfer.model.UpdateCustomerRequest
import com.example.speedotransfer.model.UpdateCustomerResponse

class EditProfileRepoImpl(remoteDataSource: RemoteDataSource) : EditProfileRepo{
    override suspend fun updateCustomerByEmail(email: String, updateRequest: UpdateCustomerRequest): UpdateCustomerResponse {
        // Mocked object for testing purposes
        val mockResponse = UpdateCustomerResponse(
            updatedAt = "2024-09-08T19:13:48.905Z",
            message = "Customer updated successfully",
            details = "Details about the update",
            httpStatusCode = HttpStatusCode(
                error = false,
                is5xxServerError = false,
                is4xxClientError = false,
                is2xxSuccessful = true,
                is1xxInformational = false,
                is3xxRedirection = false
            )
        )

        // Uncomment this when the API is live
        // return remoteDataSource.updateCustomerByEmail(email, updateRequest)

        return mockResponse // Using mock object until the API is live
    }

}