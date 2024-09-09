package com.example.speedotransfer.model

data class CustomerResponse(
    val id: Long,              // Unique customer ID
    val name: String,          // Customer's full name
    val email: String,         // Customer's email address
    val phoneNumber: String,   // Customer's phone number
    val createdAt: String,     // Timestamp of when the customer was created
    val updatedAt: String      // Timestamp of the last update
)