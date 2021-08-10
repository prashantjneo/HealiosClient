package com.healios.io.assignment.app_base_component

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HealiosApp : Application() {


    companion object {
        lateinit var instance: HealiosApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}