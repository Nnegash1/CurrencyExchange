package com.example.exchange.viewmodel

import com.example.exchange.model.data.ExchangeData

data class UIState(
    val to: String = "", val from: String = "", val exchangeDataResponse: ExchangeData? = null
)
