package com.example.bitcoinconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bitcoinconverter.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.convertButton.setOnClickListener { calculateConvert() }
    }

    private fun calculateConvert() {
        val conversion = binding.nairaAmountHint.text.toString().toDoubleOrNull()
        if (conversion == null || conversion == 0.00) {
            displayResult(0.00)
            return
        }


        val rate = conversion / 20678000.05
        var convert = String.format("%.2f", rate).toDouble()
        if (binding.switchroundup.isChecked) {
            convert = convert.let { kotlin.math.round(it) }
        }
        displayResult(convert)
    }

    private fun displayResult(convert: Double) {
        binding.result.text =
            getString(R.string.result, NumberFormat.getNumberInstance().format(convert))
    }
}