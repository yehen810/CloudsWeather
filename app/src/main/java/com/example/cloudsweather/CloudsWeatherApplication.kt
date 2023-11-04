package com.example.cloudsweather


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class CloudsWeatherApplication : Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        //彩云天气的令牌值
        const val  TOKEN = "0VCREPHjudOhx6GG"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}