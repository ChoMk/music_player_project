package com.example.musicplayproject

import android.content.Context
import android.content.Intent
import com.example.musicplayproject.Service.MusicService

fun Context.sendIntentToService(action:String){
    Intent(this, MusicService::class.java).apply {
        this.action = action
        startService(this)
    }

}