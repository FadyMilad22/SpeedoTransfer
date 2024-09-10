package com.example.speedotransfer.data.repository.transfer

import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.TransferRequest
import com.example.speedotransfer.model.TransferResponse

class TransferRepoImpl(remoteDataSource: RemoteDataSource) : TransferRepo {


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


}