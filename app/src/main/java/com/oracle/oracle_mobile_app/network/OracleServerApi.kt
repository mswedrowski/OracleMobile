package com.oracle.oracle_mobile_app.network

import com.oracle.oracle_mobile_app.data.model.AmountOfOrders
import com.oracle.oracle_mobile_app.data.model.HistoryPurchase
import com.oracle.oracle_mobile_app.data.model.TodayData
import com.oracle.oracle_mobile_app.data.model.TodayOrderDistribution
import com.oracle.oracle_mobile_app.data.model.request.UserLoginRequest
import com.oracle.oracle_mobile_app.data.response.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OracleServerApi {

    @GET("/api/mobile_user/")
    fun getUsers(): Single<Response<List<UserResponse>>>

    @GET("/api/order_amount/")
    fun getOrdersAmount(): Single<Response<List<AmountOfOrders>>>

    @GET("/api/history_purchase/")
    fun getHistoryPurchase(): Single<Response<List<HistoryPurchase>>>

    @GET("/api/today_order_dist/")
    fun getTodayOrderDistr(): Single<Response<List<TodayOrderDistribution>>>

    @GET("/api/today_data")
    fun getTodayData(): Single<Response<TodayData>>

    @GET("/api/prediction_order")
    fun getPredictionOrder(): Single<Response<List<AmountOfOrders>>>



    @POST("api/auth/login/")
    fun postLoginUser(@Body userlogin : UserLoginRequest)  : Single<Response<UserResponse>>

    @POST("api/login")
    fun login(@Body userlogin : UserLoginRequest)  : Single<Response<UserResponse>>

}