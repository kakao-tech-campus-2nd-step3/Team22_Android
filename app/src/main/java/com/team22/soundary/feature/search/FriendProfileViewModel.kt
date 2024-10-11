package com.team22.soundary.feature.search

import androidx.lifecycle.ViewModel
import com.team22.soundary.feature.search.data.model.FriendEntity
import com.team22.soundary.feature.search.data.repository.FriendRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FriendProfileViewModel : ViewModel() {

    private val friendRepository = FriendRepository()

    private val _friendProfile = MutableStateFlow<FriendEntity?>(null)
    val friendProfile: StateFlow<FriendEntity?> get() = _friendProfile.asStateFlow()

    fun loadFriendProfile(friendId: String) {
        val profile = friendRepository.getFriendById(friendId)
        _friendProfile.value = profile
    }
}
