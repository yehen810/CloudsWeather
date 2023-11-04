package com.example.cloudsweather.logic.Network

import com.example.cloudsweather.CloudsWeatherApplication
import com.example.cloudsweather.logic.Model.PlaceResponse
import retrofit2.Call


import retrofit2.http.GET
import retrofit2.http.Query
import javax.security.auth.callback.Callback

interface PlaceService {

    //只有城市参数是要动态指定的
    @GET("v2/place?token=${CloudsWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String):Call<PlaceResponse>
}