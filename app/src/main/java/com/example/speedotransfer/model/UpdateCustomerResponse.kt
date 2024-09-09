package com.example.speedotransfer.model


data class UpdateCustomerResponse(
    val updatedAt: String,    // Timestamp of when the customer was updated
    val message: String,      // Response message
    val details: String,      // Additional details, if any
    val httpStatusCode: HttpStatusCode // HTTP status code object
)
