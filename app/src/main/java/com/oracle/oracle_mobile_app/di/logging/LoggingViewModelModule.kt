package com.oracle.oracle_mobile_app.di.logging

import androidx.lifecycle.ViewModel
import com.oracle.oracle_mobile_app.di.ViewModelKey
import com.oracle.oracle_mobile_app.ui.logging.LoggingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoggingViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoggingViewModel::class)
    abstract fun LoggingViewModelModule(viewModel: LoggingViewModel): ViewModel

}