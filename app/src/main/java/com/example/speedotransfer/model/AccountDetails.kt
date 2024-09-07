data class AccountDetails(
        val id: Long,                     // Account ID
        val accountNumber: String,        // Account number
        val accountType: String,          // Type of account (e.g., CHECKING, SAVINGS)
        val balance: Double,              // Balance in the account
        val currency: String,             // Currency code (e.g., EGY)
        val accountName: String,          // Name of the account
        val accountDescription: String,   // Description of the account
        val active: Boolean,              // Whether the account is active
        val createdAt: String,            // Account creation timestamp
        val updatedAt: String             // Last update timestamp for the account
    )