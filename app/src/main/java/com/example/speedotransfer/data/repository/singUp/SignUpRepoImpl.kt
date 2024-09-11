package com.example.speedotransfer.data.repository.singUp

import RegisterCustomerRequest
import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.RegisterCustomerResponse

class SignUpRepoImpl(private val remoteDataSource: RemoteDataSource): SignUpRepo {
    override suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse {

        return remoteDataSource.registerCustomer(registerRequest)

    }
}