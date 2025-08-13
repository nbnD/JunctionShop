package com.flutterjunction.junctionshop

import android.app.Application

class JunctionShopApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        Graph.provide(context = this)
    }
}