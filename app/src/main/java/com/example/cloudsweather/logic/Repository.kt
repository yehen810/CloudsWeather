package com.example.cloudsweather.logic

import androidx.lifecycle.liveData
import com.example.cloudsweather.logic.Model.Place
import com.example.cloudsweather.logic.Network.CloudsWeatherNetwork
import com.example.cloudsweather.logic.Network.PlaceService
import kotlinx.coroutines.Dispatchers

object Repository {

    fun searchPlaces(query:String) = liveData(Dispatchers.IO){
        val result = try{
            val placeResponse = CloudsWeatherNetwork.searchPlaces(query)
            if(placeResponse.status == "ok"){
                val places = placeResponse.places
                //包装获得的城市数据列表
                Result.success(places)
            }else{
                Result.failure(java.lang.RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e :Exception){
            Result.failure<List<Place>>(e)
        }
        //将包装结果发射出去
        emit(result)
    }
}