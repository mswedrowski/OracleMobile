package com.oracle.oracle_mobile_app.ui.logging

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import com.oracle.oracle_mobile_app.R
import androidx.lifecycle.ViewModelProviders
import com.oracle.oracle_mobile_app.base.BaseActivity
import com.oracle.oracle_mobile_app.data.model.request.UserLoginRequest
import com.oracle.oracle_mobile_app.databinding.ActivityLoggingBinding
import com.oracle.oracle_mobile_app.network.OracleServerApi
import com.oracle.oracle_mobile_app.ui.main.MainActivity
import com.oracle.oracle_mobile_app.utils.extensions.toBodyOrError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


import kotlinx.android.synthetic.main.activity_logging.*
import javax.inject.Inject
class LoggingActivity @Inject constructor(
) : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityLoggingBinding
    override lateinit var viewModel: LoggingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoggingViewModel::class.java)

        login_btn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)

            if(input_password.text.isEmpty() || input_password.text.isEmpty()){
                Toast.makeText(this,"Please, give password and email",Toast.LENGTH_LONG).show()
            }
            else {
                viewModel.oracleServerApi.login(UserLoginRequest(input_email.text.toString(),input_password.text.toString()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError { err -> Log.v("ErrorAPIUser",err.toString()) }
                    .map { it.toBodyOrError() }
                    .subscribe(
                        {startActivity(intent)},
                        {err -> Log.v("ErrorAPIUser",err.toString())}
                    )
            }
        }
    }
}
