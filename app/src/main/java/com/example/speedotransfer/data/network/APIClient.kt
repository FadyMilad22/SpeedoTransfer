package com.example.speedotransfer.data.network

import AccountDetailsResponse
import CreateAccountRequest
import CreateAccountResponse
import CustomerDetailsResponse
import LoginRequest
import LoginResponse
import RegisterCustomerRequest
import RegisterCustomerResponse


object APIClient : RemoteDataSource {



    // APIClient function to register a new customer
    override suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse {
        return APIHelper.callable.registerCustomer(registerRequest)
    }
    // APIClient function to log in and generate JWT token
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
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



}