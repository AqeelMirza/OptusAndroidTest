package com.aqeel.optusandroid.network

import com.aqeel.optusandroid.model.UserInfo
import com.aqeel.optusandroid.model.UserPhotosResponse
import retrofit2.http.GET


interface ApiService {

    @GET("users")
    suspend fun getUserData(
    ): List<UserInfo>

    @GET("photos")
    suspend fun userPhotos(
    ): List<UserPhotosResponse>


}