package com.example.speedotransfer.model

data class Transaction(
    val name: String,
    val cardType: String,
    val cardNumber: String, //Take care : Used as Account number in Notification Screen
    val amount: String,
    val date: String,
    val status: String,
    val currency :String,
    val isReceived :Boolean = true,
    val isSucessful :Boolean = false
)

