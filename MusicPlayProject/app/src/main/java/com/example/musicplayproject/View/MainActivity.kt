package com.example.musicplayproject.View

import android.bluetooth.BluetoothDevice
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicplayproject.*
import com.example.musicplayproject.BR.BluetoothReceiver
import com.example.musicplayproject.Data.Player
import com.example.musicplayproject.Observer.PlayerObserver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PlayerObserver {
    private val musicReceiver =
        BluetoothReceiver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.registerPlayerObserver(this)
        registerReceiver()
        initButton()

    }




    override fun onDestroy() {
        unregisterReceiver(musicReceiver)
        sendIntentToService(FINISH)//여기서 피니시를 날려주면서 음악 정지... App 끝에서 날리면 바로 안날라감...
        App.unregisterPlayerObserver(this)
        super.onDestroy()
    }


    private fun initButton() {
        musicButton.text =
            App.getCurrentPlayerState()
        musicButton.setOnClickListener {
            if (App.getCurrentPlayerState() == PLAY) {
                sendIntentToService(PAUSE_ACTION)

            } else {
                sendIntentToService(PLAY_ACTION)

            }

        }
    }


    private fun registerReceiver() {
        val musicStateIntent = IntentFilter()
        musicStateIntent.addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
        musicStateIntent.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
        registerReceiver(musicReceiver, musicStateIntent)
    }

    override fun updatePlayState(player: Player) {
        musicButton.text = player.playState
    }


}
