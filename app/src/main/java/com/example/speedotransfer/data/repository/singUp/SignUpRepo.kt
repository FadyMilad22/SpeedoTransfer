package com.example.speedotransfer.data.repository.singUp

import RegisterCustomerRequest
import RegisterCustomerResponse

interface SignUpRepo {
    suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse

}