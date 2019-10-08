package com.oracle.oracle_mobile_app.network

import com.oracle.oracle_mobile_app.data.response.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface OracleServerApi {

    @GET("/api/mobile_user/")
    fun getUsers(): Single<Response<List<UserResponse>>>
}