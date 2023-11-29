package com.example.data.model.paymentsresponse

data class PaymentsResponse(
    val response: List<Payment>,
    val success: String
)