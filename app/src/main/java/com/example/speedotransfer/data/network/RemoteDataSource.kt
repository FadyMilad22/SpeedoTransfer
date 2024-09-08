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

interface RemoteDataSource {



    suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse
    suspend fun login(loginRequest: SignInRequest): SignInResponse
    suspend fun getCustomerById(customerId: Long): CustomerDetailsResponse
    suspend fun createAccount(newAccountData: CreateAccountRequest): CreateAccountResponse
    suspend fun getAccountById(accountId: Long): AccountDetailsResponse
    suspend fun transferMoney(transferRequest: Transfer): Transfer
    suspend fun getTransactionHistory(accountId: Long, startDate: String, endDate: String): List<TransactionResponse>
    suspend fun getTransactionById(transactionId: Long): TransactionResponse
}