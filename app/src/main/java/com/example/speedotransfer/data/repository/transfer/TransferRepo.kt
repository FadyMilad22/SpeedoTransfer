package com.example.speedotransfer.data.repository.transfer

import com.example.speedotransfer.model.ResponseHistory
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.TransferRequest
import com.example.speedotransfer.model.TransferResponse

interface TransferRepo {


    suspend fun getTransactionHistory(token:String): ResponseHistory //List<TransactionResponse>
    suspend fun getTransactionById(token: String,transactionId: Long): TransactionResponse
    suspend fun transferMoney(transferRequest: TransferRequest): TransferResponse


}