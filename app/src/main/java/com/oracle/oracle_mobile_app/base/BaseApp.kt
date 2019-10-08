package com.oracle.oracle_mobile_app.base

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.oracle.oracle_mobile_app.di.DaggerAppComponent

class BaseApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}