package com.example.exchange.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchange.model.Repository
import com.example.exchange.model.data.ExchangeCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeViewModel : ViewModel() {

    private val _state: MutableStateFlow<UIState> = MutableStateFlow((UIState()))
    val state get() = _state.asStateFlow()

    fun exchange(amount: Double) = viewModelScope.launch {
        state.value.run {
            val result = Repository.currencyExchange(to, from, amount)
            _state.value = UIState(to, from, result)
            Log.d(ExchangeViewModel::javaClass.name, "exchange: ${result.toString()}")

        }
    }

    fun updateState(uiState: UIState) {
        _state.value = uiState
    }
}