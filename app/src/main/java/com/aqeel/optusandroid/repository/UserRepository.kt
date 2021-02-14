package com.aqeel.optusandroid.repository

import com.aqeel.optusandroid.model.UserInfo
import com.aqeel.optusandroid.model.UserPhotosResponse
import com.aqeel.optusandroid.network.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) : IRepo {

    override suspend fun getUserData(): List<UserInfo> {
        return apiService.getUserData()
    }

    override suspend fun getUserAlbumData(): List<UserPhotosResponse> {
        return apiService.userPhotos()
    }


}