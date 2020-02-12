package com.example.musicplayproject

import android.app.Application
import android.content.Intent
import com.example.musicplayproject.Data.Player
import com.example.musicplayproject.Observer.PlayerObserver
import com.example.musicplayproject.Observer.Subject
import com.example.musicplayproject.Service.MusicService
import java.util.ArrayList

class App : Application(), Subject{

    private val player = Player()
    private val observers = ArrayList<PlayerObserver>()
    init{
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Intent(this, MusicService::class.java).apply {
            startService(this)
        }//여기서 앱이 시작함과 동시에 플레이어가 돌아간다는 것을 알려주자.
    }

    companion object {
        private var instance: App? = null
        fun getCurrentPlayerState() : String {
            return instance!!.player.playState!!
        }
        fun setCurrentPlayerState(state:String){
            instance!!.player.playState = state
            instance!!.notifyObservers()

        }
        fun registerPlayerObserver(observer: PlayerObserver) {
            instance!!.registerObserver(observer)
        }
        fun unregisterPlayerObserver(observer: PlayerObserver) {
            instance!!.unregisterObserver(observer)

        }

    }

    override fun registerObserver(observer: PlayerObserver) {
        if(!observers.contains(observer)){
            observers.add(observer)
        }
    }

    override fun unregisterObserver(observer: PlayerObserver) {
        observers.remove(observer)

    }

    override fun notifyObservers() {
        for (itemIDX in 0 until observers.size){
            observers[itemIDX].updatePlayState(player)
        }
    }
}