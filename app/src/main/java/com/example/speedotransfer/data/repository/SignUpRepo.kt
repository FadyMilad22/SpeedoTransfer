package com.example.speedotransfer.data.repository

import RegisterCustomerRequest
import RegisterCustomerResponse

interface SignUpRepo {
    suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse

}