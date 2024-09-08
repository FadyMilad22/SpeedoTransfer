package com.example.speedotransfer.data.repository

import AccountDetailsResponse
import CreateAccountRequest
import CreateAccountResponse
import CustomerDetailsResponse
import LoginRequest
import LoginResponse
import RegisterCustomerRequest
import RegisterCustomerResponse
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.Transfer


interface Repo {


    suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse
    suspend fun login(loginRequest: LoginRequest): LoginResponse

    suspend fun getCustomerById(customerId: Long): CustomerDetailsResponse

    suspend fun createAccount(newAccountData: CreateAccountRequest): CreateAccountResponse
    suspend fun getAccountById(accountId: Long): AccountDetailsResponse

    suspend fun getTransactionHistory(accountId: Long, startDate: String, endDate: String): List<TransactionResponse>
    suspend fun getTransactionById(transactionId: Long): TransactionResponse

    suspend fun transferMoney(transferRequest: Transfer): Transfer

}