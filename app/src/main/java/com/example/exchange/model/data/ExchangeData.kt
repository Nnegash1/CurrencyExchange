package com.example.exchange.model.data

data class ExchangeData(
    val date: String,
    val info: Info,
    val query: Query,
    val result: Double,
    val success: Boolean
)