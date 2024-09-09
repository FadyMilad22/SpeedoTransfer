package com.example.speedotransfer.data.repository

import com.example.speedotransfer.model.UpdateCustomerRequest
import com.example.speedotransfer.model.UpdateCustomerResponse

interface EditProfileRepo {
    suspend fun updateCustomerByEmail(email: String, updateRequest: UpdateCustomerRequest): UpdateCustomerResponse

}