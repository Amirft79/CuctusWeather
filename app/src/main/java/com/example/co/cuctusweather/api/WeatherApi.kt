package com.example.co.cuctusweather.api

import com.example.co.cuctusweather.data.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {

    @GET("weather/${WeatherName}")
    suspend fun getWeather(): Response<Weather>


    companion object{
        const val WeatherName:String = "London"
    }


}