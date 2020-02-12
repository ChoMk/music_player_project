package com.example.musicplayproject.Observer

import com.example.musicplayproject.Data.Player

interface PlayerObserver {
    fun updatePlayState(player:Player)
}