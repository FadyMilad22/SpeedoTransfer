package com.example.speedotransfer.data.repository

import com.example.speedotransfer.model.TransactionResponse

interface TransactionRepo {

    suspend fun getTransactionHistory(accountId: Long, startDate: String, endDate: String): List<TransactionResponse>
    suspend fun getTransactionById(transactionId: Long): TransactionResponse

}