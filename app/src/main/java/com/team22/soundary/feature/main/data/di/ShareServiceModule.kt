package com.team22.soundary.feature.main.data.di

import com.team22.soundary.feature.main.data.remote.ShareService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShareServiceModule {

    @Provides
    @Singleton
    fun provideShareService(retrofit: Retrofit): ShareService =
        retrofit.create(ShareService::class.java)
}