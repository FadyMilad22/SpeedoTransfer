package com.example.speedotransfer.model

data class Transaction(
    val name: String,
    val cardType: String,
    val cardNumber: String,
    val amount: String,
    val date: String,
    val status: String,
    val currency :String
)