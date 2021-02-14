package com.aqeel.optusandroid.di


import com.aqeel.optusandroid.network.ApiService
import com.aqeel.optusandroid.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideDataRepository(apiService: ApiService): UserRepository {
        return UserRepository(apiService)
    }

}