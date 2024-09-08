package com.example.e_commerceapp.HomeActivity.Settiings.Repo


import AccountDetails
import AccountDetailsResponse
import CreateAccountRequest
import CreateAccountResponse
import CustomerDetailsResponse
import LoginRequest
import LoginResponse
import RegisterCustomerRequest
import RegisterCustomerResponse
import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.data.repository.Repo
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.Transfer

class RepoImpl(
    val remoteDataSource: RemoteDataSource,
) : Repo {


    // Repo function to register a new customer using repository pattern
    override suspend fun registerCustomer(registerRequest: RegisterCustomerRequest): RegisterCustomerResponse {
        // Mocked object for testing purposes
        val mockResponse = RegisterCustomerResponse(
            id = 1,
            name = "John Doe",
            email = "johndoe@example.com",
            createdAt = "2024-09-04T02:47:40.62069",
            updatedAt = "2024-09-04T02:47:40.62069"
        )

        // Uncomment this when the API is live
        // return remoteDataSource.registerCustomer(registerRequest)

        return mockResponse // Using mock object until the API is live
    }

    // Repo function to log in using repository pattern
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        // Mocked object for testing purposes
        val mockResponse = LoginResponse(
            message = "Login Successful",
            token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2RhQGdtYWlsLmNvbSIsImlhdCI6MTcyNTQwNzQ2OSwiZXhwIjoxNzI1NDkzODY5fQ.i1qi6vdOjMElgd4mNc2MAnkbhX9sLihmUZLbVTRaLyk",
            tokenType = "Bearer",
            status = "ACCEPTED"
        )
        // Uncomment this when the API is live
        // return remoteDataSource.login(loginRequest)

        return mockResponse // Using mock object until the API is live
    }


    // Repo function to get customer details by ID using repository pattern
    override suspend fun getCustomerById(customerId: Long): CustomerDetailsResponse {
        // Mocked object for testing purposes
        val mockResponse = CustomerDetailsResponse(
            id = customerId,
            name = "Jane Doe",
            email = "janedoe@example.com",
            createdAt = "2024-09-04T02:47:40.62069",
            updatedAt = "2024-09-04T02:47:40.62069",
            accounts = listOf(
                AccountDetails(
                    id = 1,
                    accountNumber = "849475494",
                    accountType = "SAVINGS",
                    balance = 5000.0,
                    currency = "EGP",
                    accountName = "Personal Savings",
                    accountDescription = "Jane's savings account",
                    active = true,
                    createdAt = "2024-09-04T02:47:40.64269",
                    updatedAt = "2024-09-04T02:47:40.64269"
                )
            )
        )
        // Uncomment this when the API is live
        // return remoteDataSource.getCustomerById(customerId)

        return mockResponse // Using mock object until the API is live
    }


    // Repo function to create a new account using repository pattern
    override suspend fun createAccount(newAccountData: CreateAccountRequest): CreateAccountResponse {
        // Mocked object for testing purposes
        val mockResponse = CreateAccountResponse(
            id = 1,
            accountNumber = "849475494",
            accountType = "SAVINGS",
            balance = 1000.0,
            currency = "USD",
            accountName = "Savings Account",
            accountDescription = "This is a mock savings account.",
            active = true,
            createdAt = "2024-09-04T00:25:11.157Z",
            updatedAt = "2024-09-04T00:25:11.157Z"
        )
        // Uncomment this when the API is live
        // return remoteDataSource.createAccount(newAccountData)

        return mockResponse // Using mock object until the API is live
    }

    // Repo function to get account details by ID using repository pattern
    override suspend fun getAccountById(accountId: Long): AccountDetailsResponse {
        // Mocked object for testing purposes
        val mockResponse = AccountDetailsResponse(
            id = accountId,
            accountNumber = "849475494",
            accountType = "CHECKING",
            balance = 2000.0,
            currency = "EUR",
            accountName = "Checking Account",
            accountDescription = "This is a mock checking account.",
            active = true,
            createdAt = "2024-09-04T02:47:40.64269",
            updatedAt = "2024-09-04T02:47:40.64269"
        )
        // Uncomment this when the API is live
        // return remoteDataSource.getAccountById(accountId)

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
            )
        )

        // Uncomment this when the API is live
        // return remoteDataSource.getTransactionHistory(accountId, startDate, endDate)

        return mockResponse // Using mock object until the API is live
    }

    // Repo function to transfer money between accounts
    override suspend fun transferMoney(transferRequest: Transfer): Transfer {
        // Mocked object for testing purposes
        val mockResponse = Transfer(
            id = 1,
            senderAccountId = transferRequest.senderAccountId,
            recipientAccountId = transferRequest.recipientAccountId,
            amount = transferRequest.amount,
            currency = transferRequest.currency,
            transactionDate = "2024-09-07T14:00:00",
            status = "COMPLETED",
            description = transferRequest.description ?: "Mock transfer"
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