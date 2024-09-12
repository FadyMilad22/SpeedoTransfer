package com.example.speedotransfer.model


data class FavouriteResponse(
    val id: Long,
    val accountNumber: String,
    val recipientName: String,
    val addedAt: String
)