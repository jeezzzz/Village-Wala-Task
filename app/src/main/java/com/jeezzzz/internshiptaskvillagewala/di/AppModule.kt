package com.jeezzzz.internshiptaskvillagewala.di

import com.jeezzzz.internshiptaskvillagewala.data.network.Service
import com.jeezzzz.internshiptaskvillagewala.data.network.RetrofitHelper
import com.jeezzzz.internshiptaskvillagewala.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): Service {
        return RetrofitHelper.getInstance()
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: Service): Repository {
        return Repository(apiService)
    }
}
