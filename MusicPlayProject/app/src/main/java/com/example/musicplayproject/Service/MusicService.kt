package com.example.musicplayproject.Service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.musicplayproject.*


class MusicService : Service() {
    private val CHANNEL_ID = "CHANNEL_ID"
    private lateinit var mediaPlayer: MediaPlayer
    private var mNotifyManager: NotificationManager? = null
    private val SERVICE_ID = 1234



    private fun musicPlay(){
        mediaPlayer.start()
        App.setCurrentPlayerState(
            PLAY
        )
    }

    private fun musicPause(){
        mediaPlayer.pause()
        App.setCurrentPlayerState(
            PAUSE
        )
    }

    override fun onCreate() {
        super.onCreate()
        App.setCurrentPlayerState(
            STOP
        )
        mediaPlayer = MediaPlayer.create(this,
            R.raw.test
        )
        mediaPlayer.setOnCompletionListener {
            Toast.makeText(this,"test",Toast.LENGTH_LONG).show()
        }
        mediaPlayer.setVolume(100F, 100F)
        createNotificationChannel()
        startForeground(SERVICE_ID, createNotification())
    }


    private fun handleFinish(){
        if(mediaPlayer!= null){
            mediaPlayer.stop()
            mediaPlayer.release()
        }
        stopForeground(true)
        stopSelf()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent!!.action){
            PAUSE_ACTION ->musicPause()
            PLAY_ACTION ->musicPlay()
            FINISH ->handleFinish()

        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null


    private fun createNotificationChannel() {
        mNotifyManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "Mascot Notification", NotificationManager.IMPORTANCE_HIGH
            )
            mNotifyManager!!.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotification(): Notification? {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Foreground service is running!")
            .setContentText("After 10 seconds, this will be stopped.")
            .build()
    }

}
