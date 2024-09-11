package com.example.speedotransfer.data.repository.transaction

import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.TransactionResponse

class TransactionRepoImpl(private val remoteDataSource: RemoteDataSource) : TransactionRepo {

    // Repo function to get transaction details by ID
    override suspend fun getTransactionById(transactionId: Long): TransactionResponse {
        // Mocked object for testing purposes
        return remoteDataSource.getTransactionById(transactionId)

    }



    // Repo function to get transaction history by account ID, start date, and end date
    override suspend fun getTransactionHistory(): List<TransactionResponse> {
        // Mocked object for testing purposes

        return remoteDataSource.getTransactionHistory()


    }

}