package com.example.speedotransfer.data.repository.transaction

import com.example.speedotransfer.model.ResponseHistory
import com.example.speedotransfer.model.TransactionResponse

interface TransactionRepo {

    suspend fun getTransactionHistory(token: String): ResponseHistory// List<TransactionResponse>
    suspend fun getTransactionById(token: String,transactionId: Long): TransactionResponse

}