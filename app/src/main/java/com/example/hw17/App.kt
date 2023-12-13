package com.example.hw17

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        context = this
    }

    companion object {
        lateinit var context: Application
    }

}