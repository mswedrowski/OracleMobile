package com.oracle.oracle_mobile_app.di

import com.oracle.oracle_mobile_app.base.BaseApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        BuildersModule::class,
        NetworkModule::class,
        ViewModelFactoryModule::class,
        AppModule::class
    )
)

interface AppComponent  : AndroidInjector<BaseApp>{

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApp>()
}