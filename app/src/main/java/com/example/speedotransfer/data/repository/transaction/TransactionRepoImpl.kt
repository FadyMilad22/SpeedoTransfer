package com.example.speedotransfer.data.repository.transaction

import android.util.Log
import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.ResponseHistory
import com.example.speedotransfer.model.TransactionResponse

class TransactionRepoImpl(private val remoteDataSource: RemoteDataSource) : TransactionRepo {

    // Repo function to get transaction details by ID
    override suspend fun getTransactionById(token: String ,transactionId: Long): TransactionResponse {
        // Mocked object for testing purposes
        Log.d("API trans" , token)
        return remoteDataSource.getTransactionById(token , transactionId)

    }



    // Repo function to get transaction history by account ID, start date, and end date
    override suspend fun getTransactionHistory(token: String): ResponseHistory{// List<TransactionResponse> {
        // Mocked object for testing purposes

        return remoteDataSource.getTransactionHistory(token)


    }

}