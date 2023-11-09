package com.example.cloudsweather.ui.Weather

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.cloudsweather.logic.ForegroundService
import com.example.cloudsweather.logic.Model.Location
import com.example.cloudsweather.logic.Repository
import com.example.cloudsweather.startService

class WeatherViewModel:ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()

    var locationLng=""
    var locationLat=""
    var placeName=""

    var title = ""
    var text = ""
    var icon = 0

    val weatherLiveData = Transformations.switchMap(locationLiveData){
        location -> Repository.refreshWeather(location.lng,location.lat)
    }

    fun refreshWeather(lng:String,lat:String){
        locationLiveData.value = Location(lng, lat)
    }

    //开始前台服务的方法
    fun startForegroundService(context: Context) {
        startService<ForegroundService>(context){
            putExtra("title",title)
            putExtra("text",text)
            putExtra("icon",icon)
        }
    }

    // 停止前台服务的方法
    fun stopForegroundService(context: Context) {
        val intent = Intent(context, ForegroundService::class.java)
        context.stopService(intent)
    }

}