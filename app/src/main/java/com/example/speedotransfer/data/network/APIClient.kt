package com.example.speedotransfer.data.network

import AccountDetailsResponse
import CreateAccountRequest
import CreateAccountResponse
import CustomerDetailsResponse
import RegisterCustomerRequest
import SignInRequest
import SignInResponse
import android.util.Log
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


object APIClient : RemoteDataSource {


    override suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse {
        Log.d("API Test SignUP @Client", "@client  ")
        return APIHelper.callable.registerCustomer(registerRequest)
    }

    override suspend fun login(loginRequest: SignInRequest): SignInResponse {
        return APIHelper.callable.login(loginRequest)
    }

    override suspend fun getCustomerById(customerId: Long): CustomerDetailsResponse {
        return APIHelper.callable.getCustomerById(customerId)
    }


    override suspend fun createAccount(newAccountData: CreateAccountRequest): CreateAccountResponse {
        return APIHelper.callable.createAccount(newAccountData)
    }

    override suspend fun getAccountById(accountId: Long): AccountDetailsResponse {
        return APIHelper.callable.getAccountById(accountId)
    }

    override suspend fun transferMoney(transferRequest: Transfer): Transfer {
        return APIHelper.callable.transferMoney(transferRequest)
    }

    // APIClient function to get transaction history by account ID, start date, and end date
    override suspend fun getTransactionHistory(token: String): ResponseHistory {//List<TransactionResponse> {
        return APIHelper.callable.getTransactionHistory(token,0,10)
    }


    // APIClient function to get transaction details by ID
    override suspend fun getTransactionById(token: String,transactionId: Long): TransactionResponse {
        return APIHelper.callable.getTransactionById(token,transactionId)
    }


    override suspend fun updateCustomerByEmail(
        email: String,
        updateRequest: UpdateCustomerRequest
    ): UpdateCustomerResponse {
        return APIHelper.callable.updateCustomerByEmail(email, updateRequest)
    }


    override suspend fun logout(token: String): LogoutResponse {
        return APIHelper.callable.logout(token)
    }


    override suspend fun getCustomerByEmail(authToken: String, email: String): CustomerResponse {
        Log.d("API Test SignIN", "Bearer $authToken")

        return APIHelper.callable.getCustomerByEmail("Bearer $authToken", email = email)
    }

    override suspend fun getAllFavourites(authToken: String): List<FavouriteResponse> {
        return APIHelper.callable.getAllFavourites("Bearer $authToken")
    }

    override suspend fun addCustomerToFavourite(
        authToken: String,
        favouriteRequest: FavouriteRequest
    ): FavouriteResponse {
        return APIHelper.callable.addCustomerToFavourite("Bearer $authToken", favouriteRequest)
    }

    override suspend fun deleteFavourite(
        authToken: String,
        favouriteId: Long
    ): DeleteFavouriteResponse {
        return APIHelper.callable.deleteFavourite("Bearer $authToken", favouriteId)
    }


}