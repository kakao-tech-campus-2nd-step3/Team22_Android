package com.team22.soundary.feature.profile.data

import retrofit2.http.GET

interface ApiService {
    @GET("profile")
    suspend fun getProfile(): ProfileResponse // profile 받기
}