package com.example.cloudsweather.utils

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cloudsweather.utils.ActivityCollector

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        /*Log.d("BaseActivity",javaClass.simpleName)*/
        LogUtil.d("BaseActivity",javaClass.simpleName)
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

}