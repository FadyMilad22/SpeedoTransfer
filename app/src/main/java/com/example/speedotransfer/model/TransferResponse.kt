package com.example.speedotransfer.model

data class TransferResponse(
    val amount: Int,
    val fromAccount: String,
    val status: Boolean,
    val timestamp: String,
    val toAccount: String,
    val transactionId: Int
)