package com.oracle.oracle_mobile_app.di

import com.oracle.oracle_mobile_app.di.logging.LoggingViewModelModule
import com.oracle.oracle_mobile_app.di.menu.MenuViewModelModule
import com.oracle.oracle_mobile_app.ui.logging.LoggingActivity
import com.oracle.oracle_mobile_app.ui.menu.MenuActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule{

    @ContributesAndroidInjector (modules = [LoggingViewModelModule::class])
    abstract fun loggingActivity() : LoggingActivity

    @ContributesAndroidInjector(modules = [MenuViewModelModule::class])
    abstract fun menuActivity() : MenuActivity

}