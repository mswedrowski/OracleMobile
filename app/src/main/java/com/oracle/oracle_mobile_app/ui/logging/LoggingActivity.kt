package com.oracle.oracle_mobile_app.ui.logging

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.oracle.oracle_mobile_app.R
import androidx.lifecycle.ViewModelProviders
import com.oracle.oracle_mobile_app.base.BaseActivity
import com.oracle.oracle_mobile_app.databinding.ActivityLoggingBinding

import kotlinx.android.synthetic.main.activity_logging.*
import javax.inject.Inject
class LoggingActivity @Inject constructor() : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityLoggingBinding
    override lateinit var viewModel: LoggingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoggingViewModel::class.java)

        login_btn.setOnClickListener{ viewModel.signIn()}
    }
}
