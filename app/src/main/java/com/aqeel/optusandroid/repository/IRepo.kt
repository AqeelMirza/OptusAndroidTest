package com.aqeel.optusandroid.repository

import com.aqeel.optusandroid.model.UserInfo
import com.aqeel.optusandroid.model.UserPhotosResponse

interface IRepo {
    suspend fun getUserData(): List<UserInfo>
    suspend fun getUserAlbumData(): List<UserPhotosResponse>
}