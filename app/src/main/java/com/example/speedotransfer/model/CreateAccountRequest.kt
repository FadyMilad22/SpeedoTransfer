data class CreateAccountRequest(
        val accountType: String,     // Type of account (e.g., CHECKING, SAVINGS)
        val currency: String,        // Currency code (e.g., EGY)
        val accountName: String,     // Account name
        val accountDescription: String,  // Description of the account
        val customerId: Long         // ID of the customer owning the account
    )