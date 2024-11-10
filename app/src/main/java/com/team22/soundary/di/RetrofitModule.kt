package com.team22.soundary.di

import com.team22.soundary.BuildConfig.BASE_URL
import com.team22.soundary.core.auth.TokenInterceptor
import com.team22.soundary.core.domain.TokenRepository
import com.team22.soundary.util.DateAsStringSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.Date
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
    private val contentType = "application/json".toMediaTypeOrNull()
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            contextual(Date::class, DateAsStringSerializer)
        }
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
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
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