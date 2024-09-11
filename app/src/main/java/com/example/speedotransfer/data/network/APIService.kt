package com.example.speedotransfer.data.network

import AccountDetailsResponse
import CreateAccountRequest
import CreateAccountResponse
import CustomerDetailsResponse
import RegisterCustomerRequest
import SignInRequest
import SignInResponse
import com.example.speedotransfer.model.CustomerResponse
import com.example.speedotransfer.model.DeleteFavouriteResponse
import com.example.speedotransfer.model.FavouriteRequest
import com.example.speedotransfer.model.FavouriteResponse
import com.example.speedotransfer.model.LogoutResponse
import com.example.speedotransfer.model.RegisterCustomerResponse
import com.example.speedotransfer.model.ResponseHistory
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.Transfer
import com.example.speedotransfer.model.UpdateCustomerRequest
import com.example.speedotransfer.model.UpdateCustomerResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {


    @POST(Constants.REGISTER_CUSTOMER_ENDPOINT)
    suspend fun registerCustomer(@Body registerRequest: RegisterCustomerRequest): RegisterCustomerResponse

    @POST(Constants.LOGIN_ENDPOINT)
    suspend fun login(@Body loginRequest: SignInRequest): SignInResponse

    @GET(Constants.GET_CUSTOMER_BY_ID_ENDPOINT)
    suspend fun getCustomerById(@Path(Constants.CUSTOMER_ID_QUERY) customerId: Long): CustomerDetailsResponse

    @POST(Constants.CREATE_ACCOUNT_ENDPOINT)
    suspend fun createAccount(@Body newAccountData: CreateAccountRequest): CreateAccountResponse

    @GET(Constants.GET_ACCOUNT_BY_ID_ENDPOINT)
    suspend fun getAccountById(@Path(Constants.ACCOUNT_ID_QUERY) accountId: Long): AccountDetailsResponse


//    @GET(Constants.TRANSACTION_HISTORY_ENDPOINT)
//    suspend fun getTransactionHistory(
//        @Query(Constants.ACCOUNT_ID_QUERY) accountId: Long,
//        @Query(Constants.START_DATE_QUERY) startDate: String,
//        @Query(Constants.END_DATE_QUERY) endDate: String
//    ): List<TransactionResponse>

    @GET(Constants.TRANSACTION_HISTORY_ENDPOINT)
    suspend fun getTransactionHistory( @Header("Authorization") token: String,
        @Query(Constants.PAGE) page: Int = 0,
        @Query(Constants.SIZE) size: Int = 10
    ):ResponseHistory


    @POST(Constants.TRANSFER_MONEY_ENDPOINT)
    suspend fun transferMoney(@Body transferRequest: Transfer): Transfer

    @GET(Constants.GET_TRANSACTION_BY_ID_ENDPOINT)
    suspend fun getTransactionById(@Header("Authorization") token: String,
                                    @Path(Constants.TRANSACTION_ID_PATH) transactionId: Long): TransactionResponse


    @PUT(Constants.UPDATE_CUSTOMER_BY_EMAIL_ENDPOINT)
    suspend fun updateCustomerByEmail(
        @Query(Constants.EMAIL_QUERY) email: String,
        @Body updateCustomerRequest: UpdateCustomerRequest
    ): UpdateCustomerResponse


    @POST(Constants.LOGOUT_ENDPOINT)
    suspend fun logout(
        @Header("Authorization") token: String
    ): LogoutResponse


    @GET(Constants.GET_CUSTOMER_BY_EMAIL_ENDPOINT)
    suspend fun getCustomerByEmail(
        @Header("Authorization") authToken: String, @Path("email") email: String
    ): CustomerResponse

    @GET(Constants.GET_ALL_FAVOURITES_ENDPOINT)
    suspend fun getAllFavourites(
        @Header("Authorization") authToken: String
    ): List<FavouriteResponse>

    @POST(Constants.ADD_FAVOURITE_ENDPOINT)
    suspend fun addCustomerToFavourite(
        @Header("Authorization") authToken: String,
        @Body favouriteRequest: FavouriteRequest
    ): FavouriteResponse

    @DELETE(Constants.DELETE_FAVOURITE_ENDPOINT)
    suspend fun deleteFavourite(
        @Header("Authorization") authToken: String,
        @Path("id") favouriteId: Long
    ): DeleteFavouriteResponse


}