data class CustomerDetailsResponse(
        val id: Long,                     // Customer ID
        val name: String,                 // Customer name
        val email: String,                // Customer email
        val createdAt: String,            // Customer creation timestamp
        val updatedAt: String,            // Last update timestamp
        val accounts: List<AccountDetails>  // List of accounts associated with the customer
    )