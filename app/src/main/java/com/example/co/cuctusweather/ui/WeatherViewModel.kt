package com.example.co.cuctusweather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.co.cuctusweather.data.Weather
import com.example.co.cuctusweather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject
constructor(private val WeatherRepository:WeatherRepository): ViewModel() {
    private val _response = MutableLiveData<Weather>()
    val weatherResponse: LiveData<Weather>
        get() = _response


    init {
        getWeather()
    }
    private fun getWeather() = viewModelScope.launch {
        WeatherRepository.getWeather().let { response ->

            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("tag", "getWeather Error: ${response.code()}")
            }
        }
    }
}