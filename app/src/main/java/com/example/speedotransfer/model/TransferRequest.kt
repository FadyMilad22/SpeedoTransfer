package com.example.speedotransfer.model

data class TransferRequest(
    val amount: Int,
    val receiverAccNumber: String,
    val sendCurrency: String,
    val senderAccNumber: String
)