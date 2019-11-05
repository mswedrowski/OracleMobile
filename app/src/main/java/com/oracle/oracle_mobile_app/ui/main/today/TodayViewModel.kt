package com.oracle.oracle_mobile_app.ui.main.today

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oracle.oracle_mobile_app.data.model.HistoryRange
import com.oracle.oracle_mobile_app.data.model.TodayOrderDistribution
import com.oracle.oracle_mobile_app.network.OracleServerApi
import com.oracle.oracle_mobile_app.utils.BASE_URL
import com.oracle.oracle_mobile_app.utils.CONNECTION_TIMEOUT_SEC
import com.oracle.oracle_mobile_app.utils.extensions.toBodyOrError
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class TodayViewModel : ViewModel() {

    var todayDistributionList = MutableLiveData<List<TodayOrderDistribution>>()

    var todayToYesterdayOrder = MutableLiveData<String>()
    var todayToYesterdayOrder_bool = MutableLiveData<Boolean>()

    var todayToYesterdayRev = MutableLiveData<String>()
    var todayToYesterdayRev_bool = MutableLiveData<Boolean>()

    lateinit var oracleServerApi: OracleServerApi

    init {

        var moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val clientko = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)

        var client = clientko.build()

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        oracleServerApi = retrofit.create(OracleServerApi::class.java)

        oracleServerApi.getTodayOrderDistr()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toBodyOrError() }
            .subscribe(
                { todayDistr ->
                    var list_distr = mutableListOf<TodayOrderDistribution>()

                    todayDistr.forEach{
                        if(it.distribution>0) {
                            it.distribution=it.distribution*100
                            list_distr.add(it)
                    }}
                    todayDistributionList.value = list_distr
                },
                { err ->  Log.v("OnFetchDistErr",err.toString()) })


        todayToYesterdayOrder.value =" Number of orders:    " + 0.15F.toString() + " %"
        todayToYesterdayOrder_bool.value = true

        todayToYesterdayRev.value = (-0.02F).toString() + " %"
        todayToYesterdayRev_bool.value = false
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
}