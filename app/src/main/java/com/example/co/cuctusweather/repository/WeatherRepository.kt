package com.example.co.cuctusweather.repository

import com.example.co.cuctusweather.api.WeatherApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository
    @Inject constructor(private val weatherApi:WeatherApi) {
        suspend fun getWeather()=weatherApi.getWeather()
}