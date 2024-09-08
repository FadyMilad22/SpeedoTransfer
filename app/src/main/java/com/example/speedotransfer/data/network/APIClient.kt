package com.example.speedotransfer.data.network

import AccountDetailsResponse
import CreateAccountRequest
import CreateAccountResponse
import CustomerDetailsResponse
import SignInRequest
import SignInResponse
import RegisterCustomerRequest
import RegisterCustomerResponse
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.Transfer


object APIClient : RemoteDataSource {



    // APIClient function to register a new customer
    override suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse {
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
    override suspend fun getTransactionHistory(accountId: Long, startDate: String, endDate: String): List<TransactionResponse> {
        return APIHelper.callable.getTransactionHistory(accountId, startDate, endDate)
    }


    // APIClient function to get transaction details by ID
    override suspend fun getTransactionById(transactionId: Long): TransactionResponse {
        return APIHelper.callable.getTransactionById(transactionId)
    }




}