package com.oracle.oracle_mobile_app.utils.extensions

import retrofit2.Response

fun <T> Response<T>.toBodyOrError(): T {
    return if (isSuccessful) {
        body()!!
    } else {
        throw HttpRequestException(code())
    }
}
