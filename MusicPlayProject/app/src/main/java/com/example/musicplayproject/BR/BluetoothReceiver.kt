package com.example.musicplayproject.BR

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.musicplayproject.PLAY_ACTION
import com.example.musicplayproject.STOP_ACTION
import com.example.musicplayproject.sendIntentToService

class BluetoothReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val intentAction: String? = intent.action

        if(intentAction!=null){
            if(intentAction == BluetoothDevice.ACTION_ACL_CONNECTED){
                playMusic(context)
            }else{
                stopMusic(context)
            }

        }



    }
    private fun playMusic(context:Context){
        context.sendIntentToService(PLAY_ACTION)
    }

    private fun stopMusic(context:Context){
        context.sendIntentToService(STOP_ACTION)
    }

}