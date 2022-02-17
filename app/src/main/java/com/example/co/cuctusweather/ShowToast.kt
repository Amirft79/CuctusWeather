package com.example.co.cuctusweather

import android.content.Context
import android.widget.Toast

fun Context.ShowToast(massage:String, duration:Int= Toast.LENGTH_SHORT){
    Toast.makeText(this,massage,duration).show()
}