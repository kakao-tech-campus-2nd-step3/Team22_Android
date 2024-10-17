package com.team22.soundary.feature.profile.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileServiceModule {
    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): ProfileApiService =
        retrofit.create(ProfileApiService::class.java)

}
