package com.team22.soundary.di

import com.team22.soundary.core.auth.TokenInterceptor
import com.team22.soundary.core.domain.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherRetrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val contentType = MediaType.parse("application/json")
    private val json = Json {
        encodeDefaults = true
    }
    @OtherRetrofit
    @Provides
    @Singleton
    fun provideOtherRetrofit(tokenRepository: TokenRepository): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType!!))
            .client(
                OkHttpClient.Builder().apply {
                    addInterceptor(
                        TokenInterceptor(tokenRepository)
                    )
                }.build()
            )
            .build()


    @AuthRetrofit
    @Provides
    @Singleton
    fun provideAuthRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType!!))
            .build()
    }

}
