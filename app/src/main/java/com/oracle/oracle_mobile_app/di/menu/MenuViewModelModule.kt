package com.oracle.oracle_mobile_app.di.menu

import androidx.lifecycle.ViewModel
import com.oracle.oracle_mobile_app.di.ViewModelKey
import com.oracle.oracle_mobile_app.ui.logging.LoggingViewModel
import com.oracle.oracle_mobile_app.ui.menu.MenuViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MenuViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    abstract fun MenuViewModelModule(viewModel: MenuViewModel): ViewModel

}