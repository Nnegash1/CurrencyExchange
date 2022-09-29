package com.example.exchange.model

import com.example.exchange.domain.ExchangeRepo
import com.example.exchange.model.data.ExchangeData
import com.example.exchange.model.data.Info
import com.example.exchange.model.data.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object Repository : ExchangeRepo {
    override suspend fun currencyExchange(to: String, from: String, amount: Double): ExchangeData? {
        val scope = CoroutineScope(Dispatchers.IO)
        val result = scope.async {
            val response = Service.getInstance.exchange(to, from, amount)
            val body = response.body()
            if (response.isSuccessful) {
                body
            } else {
                ExchangeData("", Info(0.0, 0), Query(0, "", ""), 0.0, success = false)
            }

        }
        return result.await()
    }
}