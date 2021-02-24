package com.tp.notificationexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.tp.notificationexample.databinding.ActivityMainBinding
import com.tp.notificationexample.utils.CHANNEL_ID
import com.tp.notificationexample.utils.CHANNEL_NAME
import com.tp.notificationexample.utils.LOG_TAG
import com.tp.notificationexample.utils.NOTIFICATION_ID

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
        val intent = Intent(this, wassupe::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {

            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val notifications = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Wassup page")
                .setContentText("Wassup content")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()

        Log.i(LOG_TAG, "oncreate called")


        val btn = binding.btn
        val notificationManager = NotificationManagerCompat.from(this)
        btn.setOnClickListener {

            notificationManager.notify(NOTIFICATION_ID, notifications)

        }


    }

    fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {

                lightColor = Color.RED
                enableLights(true)
            }

            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            nm.createNotificationChannel(notificationChannel)
        }
    }
}