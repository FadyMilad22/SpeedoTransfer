package com.example.speedotransfer.data.repository

import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.TransactionResponse

class TransactionRepoImpl(remoteDataSource: RemoteDataSource) :TransactionRepo{

    // Repo function to get transaction details by ID
    override suspend fun getTransactionById(transactionId: Long): TransactionResponse {
        // Mocked object for testing purposes
        val mockResponse = TransactionResponse(
            id = transactionId,
            senderAccountId = 1,
            recipientAccountId = 2,
            amount = 300.0,
            currency = "USD",
            transactionDate = "2024-09-07T16:00:00",
            status = "COMPLETED",
            description = "Mock transaction"
        )

        // Uncomment this when the API is live
        // return remoteDataSource.getTransactionById(transactionId)

        return mockResponse // Using mock object until the API is live
    }



    // Repo function to get transaction history by account ID, start date, and end date
    override suspend fun getTransactionHistory(accountId: Long, startDate: String, endDate: String): List<TransactionResponse> {
        // Mocked object for testing purposes
        val mockResponse = listOf(
            TransactionResponse(
                id = 1,
                senderAccountId = accountId,
                recipientAccountId = 2,
                amount = 500.0,
                currency = "USD",
                transactionDate = "2024-09-07T12:00:00",
                status = "COMPLETED",
                description = "Mock transaction 1"
            ),
            TransactionResponse(
                id = 2,
                senderAccountId = accountId,
                recipientAccountId = 3,
                amount = 250.0,
                currency = "USD",
                transactionDate = "2024-09-08T12:00:00",
                status = "PENDING",
                description = "Mock transaction 2"
            ), TransactionResponse(
                id = 1,
                senderAccountId = accountId,
                recipientAccountId = 2,
                amount = 500.0,
                currency = "USD",
                transactionDate = "2024-09-07T12:00:00",
                status = "COMPLETED",
                description = "Mock transaction 1"
            ),
            TransactionResponse(
                id = 2,
                senderAccountId = accountId,
                recipientAccountId = 3,
                amount = 250.0,
                currency = "USD",
                transactionDate = "2024-09-08T12:00:00",
                status = "PENDING",
                description = "Mock transaction 2"
            ), TransactionResponse(
                id = 1,
                senderAccountId = accountId,
                recipientAccountId = 2,
                amount = 500.0,
                currency = "USD",
                transactionDate = "2024-09-07T12:00:00",
                status = "COMPLETED",
                description = "Mock transaction 1"
            ),
            TransactionResponse(
                id = 2,
                senderAccountId = accountId,
                recipientAccountId = 3,
                amount = 250.0,
                currency = "USD",
                transactionDate = "2024-09-08T12:00:00",
                status = "PENDING",
                description = "Mock transaction 2"
            ), TransactionResponse(
                id = 1,
                senderAccountId = accountId,
                recipientAccountId = 2,
                amount = 500.0,
                currency = "USD",
                transactionDate = "2024-09-07T12:00:00",
                status = "COMPLETED",
                description = "Mock transaction 1"
            ),
            TransactionResponse(
                id = 2,
                senderAccountId = accountId,
                recipientAccountId = 3,
                amount = 250.0,
                currency = "USD",
                transactionDate = "2024-09-08T12:00:00",
                status = "PENDING",
                description = "Mock transaction 2"
            )
        )

        // Uncomment this when the API is live
        // return remoteDataSource.getTransactionHistory(accountId, startDate, endDate)

        return mockResponse // Using mock object until the API is live
    }

}