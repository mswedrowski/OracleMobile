package com.oracle.oracle_mobile_app.ui.main.predictions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oracle.oracle_mobile_app.data.model.AmountOfOrders
import com.oracle.oracle_mobile_app.data.model.HistoryRange
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

class PredictionsViewModel : ViewModel() {

    var predictionsOrdersList  = MutableLiveData<List<AmountOfOrders>>()

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


        oracleServerApi.getPredictionOrder()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toBodyOrError() }
            .subscribe({
                    predictions -> predictionsOrdersList.postValue(predictions)},
                {err -> Log.v("OrderPredictionErr",err.toString())})

        predictionsOrdersList.value
    }

}