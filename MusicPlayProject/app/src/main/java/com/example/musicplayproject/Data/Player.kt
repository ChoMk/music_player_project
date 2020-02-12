package com.example.musicplayproject.Data

import com.example.musicplayproject.PLAY
import com.example.musicplayproject.STOP

class Player (
    var playSongID:String ?= "",
    var playSongName:String?="",
    var playState:String?= STOP
)