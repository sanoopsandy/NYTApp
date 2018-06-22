package com.example.nytapp.base

import android.app.Application
import com.example.nytapp.core.Constants
import com.example.nytapp.core.di.AppModule
import com.example.nytapp.core.di.BaseComponent
import com.example.nytapp.core.di.DaggerBaseComponent
import com.example.nytapp.core.di.NetModule
import com.facebook.stetho.Stetho

class BaseApplication : Application() {

    companion object {
        lateinit var baseComponent: BaseComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
        Stetho.initializeWithDefaults(this);
    }

    private fun initDI() {
        baseComponent = DaggerBaseComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(Constants.BASE_URL))
                .build()
    }

}