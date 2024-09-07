data class RegisterCustomerResponse(
        val id: Long,           // Customer ID
        val name: String,       // Customer name
        val email: String,      // Customer email
        val createdAt: String,  // Registration timestamp
        val updatedAt: String   // Update timestamp
    )