package com.oracle.oracle_mobile_app.ui.menu

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.oracle.oracle_mobile_app.R
import com.oracle.oracle_mobile_app.base.BaseActivity
import com.oracle.oracle_mobile_app.ui.logging.LoggingViewModel
import javax.inject.Inject

class MenuActivity @Inject constructor(): BaseActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    //private lateinit var binding: ActivityMenuActivityBinding

    override lateinit var viewModel: MenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MenuViewModel::class.java)
    }
}
