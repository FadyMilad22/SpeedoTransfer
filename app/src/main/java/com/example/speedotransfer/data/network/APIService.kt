package com.example.speedotransfer.data.network

import AccountDetailsResponse
import CreateAccountRequest
import CreateAccountResponse
import CustomerDetailsResponse
import LoginRequest
import LoginResponse
import RegisterCustomerRequest
import RegisterCustomerResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {


    // APIHelper function to register a new customer
    @POST("/api/v1/auth/register")
    suspend fun registerCustomer(@Body registerRequest: RegisterCustomerRequest): RegisterCustomerResponse

    // APIHelper function to log in and generate JWT token
    @POST("/api/v1/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse


    // APIHelper function to get customer details by ID
    @GET("/api/v1/customer/{customerId}")
    suspend fun getCustomerById(@Path("customerId") customerId: Long): CustomerDetailsResponse

    // APIHelper function to create a new account
    @POST("/api/v1/account")
    suspend fun createAccount(@Body newAccountData: CreateAccountRequest): CreateAccountResponse


    // APIHelper function to get account details by ID
    @GET("/api/v1/account/{accountId}")
    suspend fun getAccountById(@Path("accountId") accountId: Long): AccountDetailsResponse


}