package com.example.co.cuctusweather.data

data class Weather(
    val description: String,
    val forecast: List<Forecast>,
    val temperature: String,
    val wind: String
)