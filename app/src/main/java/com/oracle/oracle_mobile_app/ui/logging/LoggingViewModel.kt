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
}