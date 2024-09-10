package com.example.speedotransfer.data.repository

import AccountDetailsResponse
import CreateAccountRequest
import CreateAccountResponse
import CustomerDetailsResponse
import SignInRequest
import SignInResponse
import RegisterCustomerRequest
import RegisterCustomerResponse
import com.example.speedotransfer.model.CustomerResponse
import com.example.speedotransfer.model.DeleteFavouriteResponse
import com.example.speedotransfer.model.FavouriteRequest
import com.example.speedotransfer.model.FavouriteResponse
import com.example.speedotransfer.model.LogoutResponse
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.Transfer
import com.example.speedotransfer.model.UpdateCustomerRequest
import com.example.speedotransfer.model.UpdateCustomerResponse


interface Repo {


    suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse
    suspend fun login(loginRequest: SignInRequest): SignInResponse

    suspend fun getCustomerById(customerId: Long): CustomerDetailsResponse

    suspend fun createAccount(newAccountData: CreateAccountRequest): CreateAccountResponse
    suspend fun getAccountById(accountId: Long): AccountDetailsResponse

    suspend fun getTransactionById(transactionId: Long): TransactionResponse

    suspend fun updateCustomerByEmail(email: String, updateRequest: UpdateCustomerRequest): UpdateCustomerResponse


    suspend fun logout(token: String): LogoutResponse
    suspend fun getCustomerByEmail(email: String): CustomerResponse
    suspend fun getAllFavourites(authToken: String): List<FavouriteResponse>
    suspend fun addCustomerToFavourite(authToken: String, favouriteRequest: FavouriteRequest): FavouriteResponse
    suspend fun deleteFavourite(authToken: String, favouriteId: Long): DeleteFavouriteResponse

}