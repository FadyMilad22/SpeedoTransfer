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
        @POST(Constants.REGISTER_CUSTOMER_ENDPOINT)
        suspend fun registerCustomer(@Body registerRequest: RegisterCustomerRequest): RegisterCustomerResponse

        // APIHelper function to log in and generate JWT token
        @POST(Constants.LOGIN_ENDPOINT)
        suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

        // APIHelper function to get customer details by ID
        @GET(Constants.GET_CUSTOMER_BY_ID_ENDPOINT)
        suspend fun getCustomerById(@Path(Constants.CUSTOMER_ID_QUERY) customerId: Long): CustomerDetailsResponse

        // APIHelper function to create a new account
        @POST(Constants.CREATE_ACCOUNT_ENDPOINT)
        suspend fun createAccount(@Body newAccountData: CreateAccountRequest): CreateAccountResponse

        // APIHelper function to get account details by ID
        @GET(Constants.GET_ACCOUNT_BY_ID_ENDPOINT)
        suspend fun getAccountById(@Path(Constants.ACCOUNT_ID_QUERY) accountId: Long): AccountDetailsResponse

}