package com.example.speedotransfer.data.repository.favourite

import com.example.speedotransfer.model.DeleteFavouriteResponse
import com.example.speedotransfer.model.FavouriteRequest
import com.example.speedotransfer.model.FavouriteResponse

interface FavRepo {
    suspend fun getAllFavourites(authToken: String): List<FavouriteResponse>
    suspend fun addCustomerToFavourite(authToken: String, favouriteRequest: FavouriteRequest): FavouriteResponse
    suspend fun deleteFavourite(authToken: String, favouriteId: Long): DeleteFavouriteResponse
}