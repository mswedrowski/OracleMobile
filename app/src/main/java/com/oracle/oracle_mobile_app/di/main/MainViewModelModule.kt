package com.oracle.oracle_mobile_app.di.main

import androidx.lifecycle.ViewModel
import com.oracle.oracle_mobile_app.di.ViewModelKey
import com.oracle.oracle_mobile_app.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun MenuViewModelModule(viewModel: MainViewModel): ViewModel

}