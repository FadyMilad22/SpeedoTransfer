package com.example.speedotransfer.model

data class CustomerResponse(
    val accounts: List<Account>,
    val birthDate: String,
    val createdAt: String,
    val email: String,
    val gender: String,
    var id: Int= -1,
    val name: String,
    val phoneNumber: String,
    val updatedAt: String,
    val username: String
)