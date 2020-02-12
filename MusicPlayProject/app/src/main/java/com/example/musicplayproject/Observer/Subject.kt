package com.example.musicplayproject.Observer

interface Subject {
    fun registerObserver(observer: PlayerObserver)
    fun unregisterObserver(observer: PlayerObserver)
    fun notifyObservers()

}