package com.example.speedotransfer.model

data class Transfer(
    val id: Long =-1,                    // Transaction ID
    val senderAccountId: Long,        // Sender's account ID
    val recipientAccountId: Long,     // Recipient's account ID
    val amount: Double,               // Amount transferred
    val currency: String,             // Currency of the transaction
    val transactionDate: String = "Not Determined",      // Date of the transaction
    val status: String,               // Status after transfer (e.g., COMPLETED, FAILED)
    val description: String?          // Optional description

)
