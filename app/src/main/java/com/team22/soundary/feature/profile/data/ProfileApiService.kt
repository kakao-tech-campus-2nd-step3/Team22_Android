package com.team22.soundary.feature.profile.data

import retrofit2.http.GET

interface ProfileApiService {
    @GET("profile")
    suspend fun getProfile(): ProfileResponse // profile 받기
}
