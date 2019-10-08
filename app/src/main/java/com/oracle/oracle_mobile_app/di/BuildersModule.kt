package com.oracle.oracle_mobile_app.di

import com.oracle.oracle_mobile_app.di.logging.LoggingViewModelModule
import com.oracle.oracle_mobile_app.ui.logging.LoggingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule{

    @ContributesAndroidInjector (modules = [LoggingViewModelModule::class])
    abstract fun loggingActivity() : LoggingActivity

}