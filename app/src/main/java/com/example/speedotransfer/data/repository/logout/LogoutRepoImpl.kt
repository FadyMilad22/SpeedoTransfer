package com.example.speedotransfer.data.repository.logout

import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.LogoutResponse

class LogoutRepoImpl(private val remoteDataSource: RemoteDataSource) : LogoutRepo {

    // Repo function to log out the user
    override suspend fun logout(token: String): LogoutResponse {
        // Mocked response for testing purposes
        val mockResponse = LogoutResponse(
            timestamp = "2024-09-08T19:13:48.927Z",
            message = "Logout successful",
            details = "User session ended",
            httpStatus = "OK"
        )

        // Uncomment this when the API is live
        // return apiClient.logout(token)

        return mockResponse // Using mock response until the API is live
    }
}