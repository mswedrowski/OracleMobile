package com.oracle.oracle_mobile_app.di

import com.oracle.oracle_mobile_app.di.logging.LoggingViewModelModule
import com.oracle.oracle_mobile_app.di.main.MainViewModelModule

import com.oracle.oracle_mobile_app.ui.logging.LoggingActivity
import com.oracle.oracle_mobile_app.ui.main.MainActivity


import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule{

    @ContributesAndroidInjector (modules = [LoggingViewModelModule::class])
    abstract fun loggingActivity() : LoggingActivity

    @ContributesAndroidInjector(modules = [MainViewModelModule::class])
    abstract fun mainActivity() : MainActivity

}