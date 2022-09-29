package com.example.exchange.domain

import com.example.exchange.model.data.ExchangeData

interface ExchangeRepo {
    suspend fun currencyExchange(to : String, from : String, amount : Double) : ExchangeData?
}