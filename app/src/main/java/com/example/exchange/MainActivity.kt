package com.example.exchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.exchange.databinding.ActivityMainBinding
import com.example.exchange.model.data.ExchangeCode
import com.example.exchange.viewmodel.ExchangeViewModel
import com.example.exchange.viewmodel.UIState
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private val vm by viewModels<ExchangeViewModel>()
    private lateinit var binding: ActivityMainBinding
    val data = ExchangeCode.values()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter2 = ArrayAdapter(this, R.layout.drop_down_option, data)
        val adapter = ArrayAdapter(this, R.layout.drop_down_option, data)
        binding.filledExposed.setAdapter(adapter)
        binding.filledExposed2.setAdapter(adapter2)


        binding.filledExposed.setOnItemClickListener { _, view, _, _ ->
            (view as? TextView)?.text?.toString()?.let {
                Log.d("tag", "To set to: $it")
                vm.updateState(vm.state.value.copy(to = it))
            }
        }

        binding.filledExposed2.setOnItemClickListener { _, view, _, _ ->
            (view as? TextView)?.text?.toString()?.let {
                Log.d("tag", "From set to: $it")
                vm.updateState(vm.state.value.copy(from = it))
            }
        }

        lifecycleScope.launchWhenResumed {
            vm.state.collect {
                it.exchangeDataResponse?.run {
                    binding.answer.text = result.toString()
                }
            }
        }

        binding.calculate.setOnClickListener {
            try {
                val amount = binding.amount.text.toString().toDouble()
                amount.let{
                    vm.exchange(it)
                }
            } catch (e : NumberFormatException){
                println(e.message)
            }

            Toast.makeText(this, "Calculating...", Toast.LENGTH_SHORT).show()
        }
    }
}