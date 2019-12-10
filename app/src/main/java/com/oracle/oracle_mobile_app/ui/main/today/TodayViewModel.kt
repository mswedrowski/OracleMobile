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

    var todayToYesterdayMessage = MutableLiveData<String>()
    var todayToYesterdayDirection = MutableLiveData<Boolean>()

    var todayCountItems = MutableLiveData<String>()
    var todayToYesterdayRevenue = MutableLiveData<String>()


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


        oracleServerApi.getTodayData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toBodyOrError() }
            .subscribe(
                {todayData ->
                    todayToYesterdayMessage.postValue(" Number of orders: ${todayData.comparision_yesterday} %")

                    if((todayData.comparision_yesterday)?.toFloat()!! >0)
                        todayToYesterdayDirection.value = true
                    else
                        todayToYesterdayDirection.value = false

                    todayCountItems.postValue(todayData.order_count.toString())
                    todayToYesterdayRevenue.postValue(todayData.revenue.toString())
                },{
                    err -> Log.v("TodayDataErr",err.toString())
                }
            )
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
}