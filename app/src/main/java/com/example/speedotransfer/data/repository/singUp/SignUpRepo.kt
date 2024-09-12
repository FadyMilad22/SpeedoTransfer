package com.example.speedotransfer.data.repository.singUp

import RegisterCustomerRequest
import com.example.speedotransfer.model.RegisterCustomerResponse

interface SignUpRepo {
    suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse

}