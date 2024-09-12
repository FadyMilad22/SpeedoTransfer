package com.example.speedotransfer.model

data class LogoutResponse(
    val timestamp: String,    // Timestamp when the logout occurred
    val message: String,      // Response message (e.g., "Logout successful")
    val details: String,      // Additional details, if any
    val httpStatus: String    // HTTP status (e.g., "OK")
)