package com.example.cloudsweather

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.cloudsweather.ui.Weather.WeatherViewModel
import com.example.cloudsweather.utils.CloudsWeatherApplication

class RefreshWeatherWorker(context: Context,params: WorkerParameters) :Worker(context,params)  {

    override fun doWork(): Result {

        return Result.success()
    }


}