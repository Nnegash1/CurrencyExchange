package com.example.exchange.model

import com.example.exchange.model.data.ExchangeData
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface Service {
    companion object {
        const val BASE_URL = "https://api.apilayer.com/"
        const val END_POINT = "exchangerates_data/convert"
        const val TO = "to"
        const val FROM = "from"
        const val AMOUNT = "amount"

        private val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(PathInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val getInstance: Service = retrofit.create()
    }

    @GET(END_POINT)
    suspend fun exchange(
        @Query(TO) to: String,
        @Query(FROM) from: String,
        @Query(AMOUNT) amount: Double
    ): Response<ExchangeData>
}