package com.example.cloudsweather.logic

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.cloudsweather.MainActivity
import com.example.cloudsweather.R

class ForegroundService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (intent != null){
            val title = intent.getStringExtra("title") ?:""
            val text = intent.getStringExtra("text") ?:""
            val icon = intent.getIntExtra("icon",0)

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channel = NotificationChannel("ForegroundService","前台Service通知",NotificationManager.IMPORTANCE_DEFAULT)
                manager.createNotificationChannel(channel)
            }

            val intent = Intent(this, MainActivity::class.java)
            val  pi = PendingIntent.getActivity(this,0,intent,0)
            val notification = NotificationCompat.Builder(this,"ForegroundService")
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(resources,icon))
                .setContentIntent(pi)
                .build()
            startForeground(1,notification)
        }

        return super.onStartCommand(intent, flags, startId)
    }

}