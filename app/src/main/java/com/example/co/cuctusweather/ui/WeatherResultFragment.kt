package com.example.co.cuctusweather.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.co.cuctusweather.R
import com.example.co.cuctusweather.api.WeatherApi
import com.example.co.cuctusweather.databinding.FragmentSearchCityBinding
import com.example.co.cuctusweather.databinding.FragmentWeatherResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherResultFragment : Fragment(R.layout.fragment_weather_result) {

    private var _Binding: FragmentWeatherResultBinding?=null
    private val binding get() = _Binding!!
    private val viewModel: WeatherViewModel by viewModels<WeatherViewModel> ()


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _Binding= FragmentWeatherResultBinding.bind(view)
        viewModel.weatherResponse.observe(viewLifecycleOwner){
            binding.apply {
                tvCityName.text = WeatherApi.WeatherName
                tvDescription.text = it.description
                tvTemperature.text = it.temperature
                tvWind.text = it.wind
                val forecast = it.forecast
                tvForecast1.text = "${forecast[0].temperature}/ ${forecast[0].wind}"
                tvForecast2.text = "${forecast[1].temperature}/ ${forecast[1].wind}"
                tvForecast3.text = "${forecast[2].temperature}/ ${forecast[2].wind}"
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _Binding=null
    }
}