package com.example.speedotransfer.data.repository.transaction

import com.example.speedotransfer.model.TransactionResponse

interface TransactionRepo {

    suspend fun getTransactionHistory(): List<TransactionResponse>
    suspend fun getTransactionById(transactionId: Long): TransactionResponse

}