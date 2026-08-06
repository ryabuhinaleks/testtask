package com.example.search_cities.features.detail.presentation

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun Long.formatPopulation(symbol: String): String {
    val formatted = String.format("%,d", this).replace(',', ' ')
    return "$formatted $symbol"
}