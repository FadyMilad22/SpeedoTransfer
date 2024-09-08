package com.example.speedotransfer.model

data class TransactionResponse(
    val id: Long,                     // Transaction ID
    val senderAccountId: Long,        // Sender's account ID
    val recipientAccountId: Long,     // Recipient's account ID
    val amount: Double,               // Transaction amount
    val currency: String,             // Transaction currency
    val transactionDate: String,      // Date of the transaction
    val status: String,               // Transaction status (e.g., COMPLETED, PENDING)
    val description: String?
)

