data class AccountDetailsResponse(
        val id: Long,
        val accountNumber: String,
        val accountType: String,
        val balance: Double,
        val currency: String,
        val accountName: String,
        val accountDescription: String,
        val active: Boolean,
        val createdAt: String,
        val updatedAt: String
    )