data class LoginResponse(
        val message: String,    // Success message
        val token: String,      // JWT token
        val tokenType: String,  // Token type (e.g., Bearer)
        val status: String      // Status of the login (e.g., ACCEPTED)
    )