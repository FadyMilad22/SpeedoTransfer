package com.example.speedotransfer.data.network

import AccountDetailsResponse
import CreateAccountRequest
import CreateAccountResponse
import CustomerDetailsResponse
import SignInRequest
import SignInResponse
import RegisterCustomerRequest
import android.util.Log
import com.example.speedotransfer.model.CustomerResponse
import com.example.speedotransfer.model.DeleteFavouriteResponse
import com.example.speedotransfer.model.FavouriteRequest
import com.example.speedotransfer.model.FavouriteResponse
import com.example.speedotransfer.model.LogoutResponse
import com.example.speedotransfer.model.RegisterCustomerResponse
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.Transfer
import com.example.speedotransfer.model.UpdateCustomerRequest
import com.example.speedotransfer.model.UpdateCustomerResponse


object APIClient : RemoteDataSource {



    // APIClient function to register a new customer
    override suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse {
        Log.d("API Test SignUP @Client","@client  ")
        return APIHelper.callable.registerCustomer(registerRequest)
    }
    // APIClient function to log in and generate JWT token
    override suspend fun login(loginRequest: SignInRequest): SignInResponse {
        return APIHelper.callable.login(loginRequest)
    }
    // APIClient function to get customer details by ID
    override suspend fun getCustomerById(customerId: Long): CustomerDetailsResponse {
        return APIHelper.callable.getCustomerById(customerId)
    }


    // APIClient function to create a new account
    override suspend fun createAccount(newAccountData: CreateAccountRequest): CreateAccountResponse {
        return APIHelper.callable.createAccount(newAccountData)
    }
    // APIClient function to get account details by ID
    override suspend fun getAccountById(accountId: Long): AccountDetailsResponse {
        return APIHelper.callable.getAccountById(accountId)
    }

    // APIClient function to transfer money between accounts
    override suspend fun transferMoney(transferRequest: Transfer): Transfer {
        return APIHelper.callable.transferMoney(transferRequest)
    }

    // APIClient function to get transaction history by account ID, start date, and end date
    override suspend fun getTransactionHistory(): List<TransactionResponse> {
        return APIHelper.callable.getTransactionHistory(0,10)
    }


    // APIClient function to get transaction details by ID
    override suspend fun getTransactionById(transactionId: Long): TransactionResponse {
        return APIHelper.callable.getTransactionById(transactionId)
    }


    override suspend fun updateCustomerByEmail(email: String, updateRequest: UpdateCustomerRequest): UpdateCustomerResponse {
        return APIHelper.callable.updateCustomerByEmail(email, updateRequest)
    }


    // API Client function for logging out the user
    override suspend fun logout(token: String): LogoutResponse {
        return APIHelper.callable.logout(token)
    }


    // API Client function to get customer details by email
    override suspend fun getCustomerByEmail(authToken: String,email: String): CustomerResponse {
        Log.d("API Test SignIN","Bearer $authToken")

        return APIHelper.callable.getCustomerByEmail("Bearer $authToken",email=email)
    }

    // API Client function to get all favourites for the logged-in customer
    override suspend fun getAllFavourites(authToken: String): List<FavouriteResponse> {
        return APIHelper.callable.getAllFavourites("Bearer $authToken")
    }

    // API Client function to add a customer to favourites
    override suspend fun addCustomerToFavourite(authToken: String, favouriteRequest: FavouriteRequest): FavouriteResponse {
        return APIHelper.callable.addCustomerToFavourite("Bearer $authToken", favouriteRequest)
    }

    // API Client function to delete a favourite
    override suspend fun deleteFavourite(authToken: String, favouriteId: Long): DeleteFavouriteResponse {
        return APIHelper.callable.deleteFavourite("Bearer $authToken", favouriteId)
    }


}