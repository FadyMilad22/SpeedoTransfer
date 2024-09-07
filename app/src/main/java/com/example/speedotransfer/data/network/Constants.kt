package com.example.speedotransfer.data.network

object Constants {

    /*TODO Add the Base URL*/
    const val BASE_URL = "Base URL"

    const val REGISTER_CUSTOMER_ENDPOINT = "/api/v1/auth/register"
    const val LOGIN_ENDPOINT = "/api/v1/auth/login"
    const val GET_CUSTOMER_BY_ID_ENDPOINT = "/api/v1/customer/{customerId}"
    const val CREATE_ACCOUNT_ENDPOINT = "/api/v1/account"
    const val GET_ACCOUNT_BY_ID_ENDPOINT = "/api/v1/account/{accountId}"

    // Query Parameters
    const val CUSTOMER_ID_QUERY = "customerId"
    const val ACCOUNT_ID_QUERY = "accountId"
}
