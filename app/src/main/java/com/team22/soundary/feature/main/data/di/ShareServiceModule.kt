package com.team22.soundary.feature.main.data.di

import com.team22.soundary.di.OtherRetrofit
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
    fun provideShareService(@OtherRetrofit retrofit: Retrofit): ShareService =
        retrofit.create(ShareService::class.java)
}