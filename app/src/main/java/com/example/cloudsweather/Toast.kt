package com.example.cloudsweather

import android.widget.Toast


fun String.showToast(duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(CloudsWeatherApplication.context,this,duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(CloudsWeatherApplication.context,this,duration).show()
}