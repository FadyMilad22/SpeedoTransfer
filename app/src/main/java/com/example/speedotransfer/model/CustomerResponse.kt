package com.example.speedotransfer.model

data class CustomerResponse(
    val accounts: List<Account>,
    val birthDate: String,
    val createdAt: String,
    val email: String,
    val gender: String,
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val updatedAt: String,
    val username: String
)