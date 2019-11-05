package com.oracle.oracle_mobile_app.ui.logging

import android.util.Log
import com.oracle.oracle_mobile_app.base.BaseViewModel
import com.oracle.oracle_mobile_app.network.OracleServerApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoggingViewModel @Inject constructor(
                                            var oracleServerApi: OracleServerApi
                                            ): BaseViewModel() {

    fun signIn() {

        disposables.add(oracleServerApi.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError{err -> Log.v("SignInError",err.toString())}
            .subscribe(
                {users -> Log.v("Fetched users",users.body().toString())}
            ))


    }
    init {
        signIn()
    }
}