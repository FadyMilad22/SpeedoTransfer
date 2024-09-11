package com.example.speedotransfer.data.network

import AccountDetailsResponse
import CreateAccountRequest
import CreateAccountResponse
import CustomerDetailsResponse
import SignInRequest
import SignInResponse
import RegisterCustomerRequest
import com.example.speedotransfer.model.CustomerResponse
import com.example.speedotransfer.model.DeleteFavouriteResponse
import com.example.speedotransfer.model.FavouriteRequest
import com.example.speedotransfer.model.FavouriteResponse
import com.example.speedotransfer.model.LogoutResponse
import com.example.speedotransfer.model.RegisterCustomerResponse
import com.example.speedotransfer.model.ResponseHistory
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.Transfer
import com.example.speedotransfer.model.UpdateCustomerRequest
import com.example.speedotransfer.model.UpdateCustomerResponse

interface RemoteDataSource {



    suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse
    suspend fun login(loginRequest: SignInRequest): SignInResponse
    suspend fun getCustomerById(customerId: Long): CustomerDetailsResponse
    suspend fun createAccount(newAccountData: CreateAccountRequest): CreateAccountResponse
    suspend fun getAccountById(accountId: Long): AccountDetailsResponse
    suspend fun transferMoney(transferRequest: Transfer): Transfer
    suspend fun getTransactionHistory(token: String): ResponseHistory// List<TransactionResponse>
    suspend fun getTransactionById(token :String ,transactionId: Long): TransactionResponse
    suspend fun updateCustomerByEmail(email: String, updateRequest: UpdateCustomerRequest): UpdateCustomerResponse
    suspend fun logout(token: String): LogoutResponse
    suspend fun getCustomerByEmail(authToken: String,email: String): CustomerResponse

    suspend fun getAllFavourites(authToken: String): List<FavouriteResponse>
    suspend fun addCustomerToFavourite(authToken: String, favouriteRequest: FavouriteRequest): FavouriteResponse
    suspend fun deleteFavourite(authToken: String, favouriteId: Long): DeleteFavouriteResponse
}
