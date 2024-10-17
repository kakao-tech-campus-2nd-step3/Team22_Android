package com.team22.soundary.feature.profile.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team22.soundary.feature.profile.data.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    fun loadProfileData() {
        viewModelScope.launch {
            val profileData = profileRepository.getProfileData()
            // 데이터 추가
        }
    }
}
