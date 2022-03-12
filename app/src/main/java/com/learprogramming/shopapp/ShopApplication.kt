package com.learprogramming.shopapp

import android.app.Application
import com.learprogramming.shopapp.commons.LoginSession

class ShopApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        LoginSession.init(this@ShopApplication)
    }
}