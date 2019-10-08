package com.oracle.oracle_mobile_app.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class UserResponse(
    @PrimaryKey val id:Int,
    val email : String,
    val password : String,
    val created_at : String
)