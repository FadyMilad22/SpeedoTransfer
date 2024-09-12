package com.example.speedotransfer.model

data class Account(
    val accountDescription: String,
    val accountName: String,
    val accountNumber: String,
    val accountType: String,
    val active: Boolean,
    val balance: Int,
    val createdAt: String,
    val currency: String,
    val id: Int,
    val updatedAt: String
)