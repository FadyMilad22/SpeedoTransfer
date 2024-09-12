data class CreateAccountResponse(
        val id: Long,                // Account ID
        val accountNumber: String,   // Generated account number
        val accountType: String,     // Account type (e.g., CHECKING, SAVINGS)
        val balance: Double,         // Initial balance
        val currency: String,        // Currency code
        val accountName: String,     // Account name
        val accountDescription: String, // Account description
        val active: Boolean,         // Whether the account is active
        val createdAt: String,       // Account creation timestamp
        val updatedAt: String        // Account update timestamp
    )