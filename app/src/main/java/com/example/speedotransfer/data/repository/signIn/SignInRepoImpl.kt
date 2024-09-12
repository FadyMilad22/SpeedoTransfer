package com.example.speedotransfer.data.repository.signIn

import SignInRequest
import SignInResponse
import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.Account
import com.example.speedotransfer.model.CustomerResponse

class SignInRepoImpl(private val remoteDataSource: RemoteDataSource): SignInRepo {

    override suspend fun signIn(signInRequest: SignInRequest): SignInResponse {
        return remoteDataSource.login(signInRequest)

    }

    // Repo function to get customer details by email
    override suspend fun getCustomerByEmail(authToken :String, email: String): CustomerResponse {
        return remoteDataSource.getCustomerByEmail(authToken,email) }

}