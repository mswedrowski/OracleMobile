package com.oracle.oracle_mobile_app

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
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import io.reactivex.android.plugins.RxAndroidPlugins



class OracleAPITest (
) {
    var oracleServerApi: OracleServerApi? = null

    @Before
    fun createOracleServerAPI(){
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val clientToBuild = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)

        val client = clientToBuild.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
        // Initialize Retrofit instance
        oracleServerApi = retrofit.create(OracleServerApi::class.java)
    }
    @Before
    fun allowRx(){
        // Allow to test Rx code
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
    }

    @Test
    fun isConnectedToBackend() {

        var isConnected : Boolean? = null

        oracleServerApi!!.getOrdersAmount()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toBodyOrError() }
            .doOnSuccess{Assert.assertEquals(true, isConnected)}
            .doOnError {Assert.assertEquals(true, isConnected)}
            .subscribe (
                {isConnected =  true},{isConnected = false})
    }

    @Test
    fun isHistoryPurchaseDataFetched(){

        var isFetched : Boolean? = null

        oracleServerApi!!.getHistoryPurchase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toBodyOrError() }
            .doOnSuccess {Assert.assertEquals(true, isFetched)}
            .doOnError   {Assert.assertEquals(true, isFetched)}
            .subscribe (
                {isFetched =  true},{isFetched = false})
    }

    @Test
    fun isTodayOrderDistrDataFetched(){

        var isFetched : Boolean? = null

        oracleServerApi!!.getTodayOrderDistr()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toBodyOrError() }
            .doOnSuccess {Assert.assertEquals(true, isFetched)}
            .doOnError   {Assert.assertEquals(true, isFetched)}
            .subscribe (
                {isFetched =  true},{isFetched = false})
    }

    @Test
    fun isTodayDataFetched(){

        var isFetched : Boolean? = null

        oracleServerApi!!.getTodayData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toBodyOrError() }
            .doOnSuccess {Assert.assertEquals(true, isFetched)}
            .doOnError   {Assert.assertEquals(true, isFetched)}
            .subscribe (
                {isFetched =  true},{isFetched = false})
    }

    @Test
    fun isHPredictionOrderDataFetched(){

        var isFetched : Boolean? = null

        oracleServerApi!!.getPredictionOrder()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toBodyOrError() }
            .doOnSuccess {Assert.assertEquals(true, isFetched)}
            .doOnError   {Assert.assertEquals(true, isFetched)}
            .subscribe (
                {isFetched =  true},{isFetched = false})
    }
}