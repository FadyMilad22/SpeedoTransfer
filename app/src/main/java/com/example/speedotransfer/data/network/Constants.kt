package com.example.speedotransfer.data.network

object Constants {

    /*TODO Add the Base URL*/
    const val BASE_URL = "Base URL"


    // Endpoints
    const val REGISTER_CUSTOMER_ENDPOINT = "/api/v1/auth/register"
    const val LOGIN_ENDPOINT = "/api/v1/auth/login"
    const val GET_CUSTOMER_BY_ID_ENDPOINT = "/api/v1/customer/{customerId}"
    const val CREATE_ACCOUNT_ENDPOINT = "/api/v1/account"
    const val GET_ACCOUNT_BY_ID_ENDPOINT = "/api/v1/account/{accountId}"
    const val TRANSACTION_HISTORY_ENDPOINT = "/api/transactionHistory"
    const val TRANSFER_MONEY_ENDPOINT = "/api/transfer"
    const val GET_TRANSACTION_BY_ID_ENDPOINT = "/api/{id}"
    const val UPDATE_CUSTOMER_BY_EMAIL_ENDPOINT = "/api/customer/update"


    // Query Parameters
    const val CUSTOMER_ID_QUERY = "customerId"
    const val ACCOUNT_ID_QUERY = "accountId"
    const val START_DATE_QUERY = "startDate"
    const val END_DATE_QUERY = "endDate"
    const val EMAIL_QUERY = "email"
    // Path Parameters
    const val TRANSACTION_ID_PATH = "id"





}
