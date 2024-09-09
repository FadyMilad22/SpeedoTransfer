package com.example.speedotransfer.model

data class UpdateCustomerRequest(
    val name: String,         // Name of the customer
    val email: String,        // Email to be updated
    val phoneNumber: String   // Phone number to be updated
)