package com.team22.soundary.feature.profile.data

import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val apiService: ProfileApiService
) {
    suspend fun getProfileData() = apiService.getProfile()
}
