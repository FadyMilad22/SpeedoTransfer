package com.example.speedotransfer.data.repository.transfer

import android.util.Log
import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.ResponseHistory
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.TransferRequest
import com.example.speedotransfer.model.TransferResponse

class TransferRepoImpl(private val remoteDataSource: RemoteDataSource) : TransferRepo {


    // Repo function to get transaction history by account ID, start date, and end date
    override suspend fun getTransactionHistory(token :String): ResponseHistory{//List<TransactionResponse> {
Log.d("API trans" , token)
      return remoteDataSource.getTransactionHistory(token )

    }

    // Repo function to transfer money between accounts
    override suspend fun transferMoney(transferRequest: TransferRequest): TransferResponse {
        // Mocked object for testing purposes
        val mockResponse = TransferResponse(
            transactionId = 1,
            fromAccount = transferRequest.senderAccNumber,
            toAccount = transferRequest.receiverAccNumber,
            amount = transferRequest.amount,
            timestamp = "2024-09-07T14:00:00",
            status = true,
            )

        // Uncomment this when the API is live
        // return remoteDataSource.transferMoney(transferRequest)

        return mockResponse // Using mock object until the API is live
    }


    // Repo function to get transaction details by ID
    override suspend fun getTransactionById(token: String,transactionId: Long): TransactionResponse {


        return remoteDataSource.getTransactionById(token,transactionId)

    }


}