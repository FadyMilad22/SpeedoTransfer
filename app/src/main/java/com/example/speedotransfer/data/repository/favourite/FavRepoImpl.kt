package com.example.speedotransfer.data.repository.favourite

import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.DeleteFavouriteResponse
import com.example.speedotransfer.model.FavouriteRequest
import com.example.speedotransfer.model.FavouriteResponse

class FavRepoImpl (private val remoteDataSource: RemoteDataSource) :FavRepo{


    // Repo function to get all favourites for the logged-in customer
    override suspend fun getAllFavourites(authToken: String): List<FavouriteResponse> {
        // Mocked response for testing purposes
        val mockResponse = listOf(
            FavouriteResponse(
                id = 1,
                accountNumber = "123456789",
                recipientName = "John Doe",
                addedAt = "2024-09-09T12:00:00"
            ),
            FavouriteResponse(
                id = 2,
                accountNumber = "987654321",
                recipientName = "Jane Doe",
                addedAt = "2024-09-09T12:00:00"
            )
        )

        // Uncomment this when the API is live
        // return apiClient.getAllFavourites(authToken)

        return mockResponse // Using mock response until the API is live
    }


    // Repo function to add a customer to favourites
    override suspend fun addCustomerToFavourite(authToken: String, favouriteRequest: FavouriteRequest): FavouriteResponse {
        // Mocked response for testing purposes
        val mockResponse = FavouriteResponse(
            id = 3,
            accountNumber = favouriteRequest.accountNumber,
            recipientName = favouriteRequest.recipientName,
            addedAt = "2024-09-09T13:00:00"
        )

        // Uncomment this when the API is live
        // return apiClient.addCustomerToFavourite(authToken, favouriteRequest)

        return mockResponse // Using mock response until the API is live
    }

    // Repo function to delete a favourite
    override suspend fun deleteFavourite(authToken: String, favouriteId: Long): DeleteFavouriteResponse {
        // Mocked response for testing purposes
        val mockResponse = DeleteFavouriteResponse(
            message = "Favourite with ID $favouriteId deleted successfully"
        )

        // Uncomment this when the API is live
        // return apiClient.deleteFavourite(authToken, favouriteId)

        return mockResponse // Using mock response until the API is live
    }


}

