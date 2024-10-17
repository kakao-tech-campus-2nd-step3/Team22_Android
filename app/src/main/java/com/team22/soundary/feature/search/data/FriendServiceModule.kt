package com.team22.soundary.feature.search.data

import com.team22.soundary.feature.search.data.api.FriendService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object FriendServiceModule {

    @Provides
    @Singleton
    fun provideFriendService(retrofit: Retrofit): FriendService =
        retrofit.create(FriendService::class.java)

}
