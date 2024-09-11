package com.example.speedotransfer.model

data class TransactionResponse(
    val amount: Double,
    val fromAccount: String,
    val status: Boolean,
    val timestamp: String,
    val toAccount: String,
    val transactionId: Int
)