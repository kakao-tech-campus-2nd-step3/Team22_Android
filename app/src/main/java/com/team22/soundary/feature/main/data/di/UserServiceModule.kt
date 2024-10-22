package com.team22.soundary.feature.main.data.di

import com.team22.soundary.di.OtherRetrofit
import com.team22.soundary.feature.main.data.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserServiceModule {

    @Provides
    @Singleton
    fun provideUserService(@OtherRetrofit retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}