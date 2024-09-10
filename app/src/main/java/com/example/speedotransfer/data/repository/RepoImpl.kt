package com.example.e_commerceapp.HomeActivity.Settiings.Repo


import AccountDetails
import AccountDetailsResponse
import CreateAccountRequest
import CreateAccountResponse
import CustomerDetailsResponse
import SignInRequest
import SignInResponse
import RegisterCustomerRequest
import RegisterCustomerResponse
import com.example.speedotransfer.data.network.RemoteDataSource
import com.example.speedotransfer.data.repository.Repo
import com.example.speedotransfer.model.Account
import com.example.speedotransfer.model.CustomerResponse
import com.example.speedotransfer.model.DeleteFavouriteResponse
import com.example.speedotransfer.model.FavouriteRequest
import com.example.speedotransfer.model.FavouriteResponse
import com.example.speedotransfer.model.HttpStatusCode
import com.example.speedotransfer.model.LogoutResponse
import com.example.speedotransfer.model.TransactionResponse
import com.example.speedotransfer.model.Transfer
import com.example.speedotransfer.model.UpdateCustomerRequest
import com.example.speedotransfer.model.UpdateCustomerResponse

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
    override suspend fun login(loginRequest: SignInRequest): SignInResponse {
        // Mocked object for testing purposes
        val mockResponse = SignInResponse(
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

    // Repo function to update customer by email

    override suspend fun updateCustomerByEmail(email: String, updateRequest: UpdateCustomerRequest): UpdateCustomerResponse {
        // Mocked object for testing purposes
        val mockResponse = UpdateCustomerResponse(
            updatedAt = "2024-09-08T19:13:48.905Z",
            message = "Customer updated successfully",
            details = "Details about the update",
            httpStatusCode = HttpStatusCode(
                error = false,
                is5xxServerError = false,
                is4xxClientError = false,
                is2xxSuccessful = true,
                is1xxInformational = false,
                is3xxRedirection = false
            )
        )

        // Uncomment this when the API is live
        // return remoteDataSource.updateCustomerByEmail(email, updateRequest)

        return mockResponse // Using mock object until the API is live
    }



    // Repo function to log out the user
    override suspend fun logout(token: String): LogoutResponse {
        // Mocked response for testing purposes
        val mockResponse = LogoutResponse(
            timestamp = "2024-09-08T19:13:48.927Z",
            message = "Logout successful",
            details = "User session ended",
            httpStatus = "OK"
        )

        // Uncomment this when the API is live
        // return apiClient.logout(token)

        return mockResponse // Using mock response until the API is live
    }

    // Repo function to get customer details by email
    override suspend fun getCustomerByEmail(email: String): CustomerResponse {
        // Mocked response for testing purposes
        // Mock Account Object
        val mockAccount = Account(
            accountDescription = "Personal Savings Account",
            accountName = "John's Savings",
            accountNumber = "1234567890",
            accountType = "Savings",
            active = true,
            balance = 5000,
            createdAt = "2023-01-01T12:00:00Z",
            currency = "USD",
            id = 1,
            updatedAt = "2023-08-01T12:00:00Z"
        )

// Mock CustomerResponse Object
        val mockResponse = CustomerResponse(
            accounts = listOf(mockAccount),  // A list of account objects
            birthDate = "1990-01-01",
            createdAt = "2022-05-01T12:00:00Z",
            email = "john.doe@example.com",
            gender = "Male",
            id = 123,
            name = "John Doe",
            phoneNumber = "+1234567890",
            updatedAt = "2023-08-01T12:00:00Z",
            username = "johndoe"
        )

        // Uncomment this when the API is live
        // return apiClient.getCustomerByEmail(email)

        return mockResponse // Using mock response until the API is live
    }

    // Repo function to get all favourites for the logged-in customer
    override suspend fun getAllFavourites(authToken: String): List<FavouriteResponse> {
        // Mocked response for testing purposes
        val mockResponse = listOf(
            FavouriteResponse(
                id = 1,
                accountNumber = "123456789",
                recipientName = "John Doe",
                addedAt = "2024-09-09T12:00:00"
            ),
            FavouriteResponse(
                id = 2,
                accountNumber = "987654321",
                recipientName = "Jane Doe",
                addedAt = "2024-09-09T12:00:00"
            )
        )

        // Uncomment this when the API is live
        // return apiClient.getAllFavourites(authToken)

        return mockResponse // Using mock response until the API is live
    }


    // Repo function to add a customer to favourites
    override suspend fun addCustomerToFavourite(authToken: String, favouriteRequest: FavouriteRequest): FavouriteResponse {
        // Mocked response for testing purposes
        val mockResponse = FavouriteResponse(
            id = 3,
            accountNumber = favouriteRequest.accountNumber,
            recipientName = favouriteRequest.recipientName,
            addedAt = "2024-09-09T13:00:00"
        )

        // Uncomment this when the API is live
        // return apiClient.addCustomerToFavourite(authToken, favouriteRequest)

        return mockResponse // Using mock response until the API is live
    }

    // Repo function to delete a favourite
    override suspend fun deleteFavourite(authToken: String, favouriteId: Long): DeleteFavouriteResponse {
        // Mocked response for testing purposes
        val mockResponse = DeleteFavouriteResponse(
            message = "Favourite with ID $favouriteId deleted successfully"
        )

        // Uncomment this when the API is live
        // return apiClient.deleteFavourite(authToken, favouriteId)

        return mockResponse // Using mock response until the API is live
    }


}

