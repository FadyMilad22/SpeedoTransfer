package com.example.speedotransfer.data.repository.transfer

import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.TransferRequest
import com.example.speedotransfer.model.TransferResponse

interface TransferRepo {


    suspend fun getTransactionHistory(accountId: Long, startDate: String, endDate: String): List<TransactionResponse>
    suspend fun getTransactionById(transactionId: Long): TransactionResponse
    suspend fun transferMoney(transferRequest: TransferRequest): TransferResponse


}