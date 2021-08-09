package com.healios.io.assignment.app_base_component

import android.app.Application

class HealiosApp : Application() {


    companion object {
        lateinit var instance: HealiosApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}