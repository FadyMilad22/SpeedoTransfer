package com.example.speedotransfer.model

data class HttpStatusCode(
    val error: Boolean,               // True if there was an error
    val is5xxServerError: Boolean,    // True if it's a 5xx server error
    val is4xxClientError: Boolean,    // True if it's a 4xx client error
    val is2xxSuccessful: Boolean,     // True if the request was successful (2xx)
    val is1xxInformational: Boolean,  // True if it's a 1xx informational response
    val is3xxRedirection: Boolean     // True if it's a 3xx redirection response
)