package com.aqeel.optusandroid.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aqeel.optusandroid.repository.UserRepository
import com.aqeel.optusandroid.utils.Resource
import kotlinx.coroutines.Dispatchers

class UserViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel() {
    fun getUserData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = repository.getUserData()))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }


    fun getUserAlbumData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = repository.getUserAlbumData()))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }


}