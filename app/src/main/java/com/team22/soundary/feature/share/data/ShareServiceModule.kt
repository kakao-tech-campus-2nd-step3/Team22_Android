package com.team22.soundary.feature.share.data

import com.team22.soundary.di.OtherRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShareServiceModule {
    @Singleton
    @Provides
    fun provideShareApiService(@OtherRetrofit retrofit: Retrofit): ShareService =
        retrofit.create(ShareService::class.java)
}