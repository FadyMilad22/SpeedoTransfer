data class RegisterCustomerRequest(
    val name: String,       // Customer name
    val email: String,      // Customer email
    val password: String,   // Customer password
    val confirmPassword: String,  // Customer password
    val country: String,
    val birthDate: String

)