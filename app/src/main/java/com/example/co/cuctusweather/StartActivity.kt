package com.example.co.cuctusweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.co.cuctusweather.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var  binding:ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}