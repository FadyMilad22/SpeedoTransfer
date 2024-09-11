package com.example.speedotransfer.data.network

object Constants {

    /*TODO Add the Base URL*/
    const val BASE_URL = "https://banquemisr-transfer-service.onrender.com"


    // Endpoints
    const val REGISTER_CUSTOMER_ENDPOINT = "/api/auth/register"
    const val LOGIN_ENDPOINT = "/api/auth/login"
    const val GET_CUSTOMER_BY_ID_ENDPOINT = "/api/customer/{customerId}"
    const val CREATE_ACCOUNT_ENDPOINT = "/api/account"
    const val GET_ACCOUNT_BY_ID_ENDPOINT = "/api/account/{accountId}"
    const val TRANSACTION_HISTORY_ENDPOINT = "/api/transactions/history"
    const val TRANSFER_MONEY_ENDPOINT = "/api/transfer/account"
    const val GET_TRANSACTION_BY_ID_ENDPOINT = "/api/{id}"
    const val UPDATE_CUSTOMER_BY_EMAIL_ENDPOINT = "/api/customer/update"
    const val LOGOUT_ENDPOINT = "/api/auth/logout"
    const val GET_CUSTOMER_BY_EMAIL_ENDPOINT = "/api/customer/email/{email}"
    const val DELETE_FAVOURITE_ENDPOINT = "/api/favourites/{id}"
    const val ADD_FAVOURITE_ENDPOINT = "/api/favourites"
    const val GET_ALL_FAVOURITES_ENDPOINT = "/api/favourites"



    // Query Parameters
    const val CUSTOMER_ID_QUERY = "customerId"
    const val ACCOUNT_ID_QUERY = "accountId"
    const val EMAIL_QUERY = "email"
    // Path Parameters
    const val PAGE ="page"
    const val SIZE="size"
    const val TRANSACTION_ID_PATH = "id"





}
