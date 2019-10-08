package com.oracle.oracle_mobile_app.di

import android.content.Context
import com.oracle.oracle_mobile_app.base.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class  AppModule {
    @Module
    companion object {
        @Provides
        @Singleton
        @JvmStatic
        fun provideAppContext(app: BaseApp): Context = app.applicationContext
    }
}