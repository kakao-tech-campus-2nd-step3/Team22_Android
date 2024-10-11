package com.team22.soundary.feature.search

import androidx.lifecycle.ViewModel
import com.team22.soundary.feature.search.data.model.FriendEntity
import com.team22.soundary.feature.search.data.repository.FriendRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FriendProfileViewModel : ViewModel() {

    private val friendRepository = FriendRepository.getInstance()

    private val _friendProfile = MutableStateFlow<FriendEntity?>(null)
    val friendProfile: StateFlow<FriendEntity?> get() = _friendProfile.asStateFlow()

    private val _isFriendAdded = MutableStateFlow<Boolean>(false)
    val isFriendAdded: StateFlow<Boolean> get() = _isFriendAdded.asStateFlow()

    fun loadFriendProfile(friendId: String) {
        val profile = friendRepository.getFriendById(friendId)
        _friendProfile.value = profile
    }

    fun addFriend(friendId: String) {
        val result = friendRepository.addFriend(friendId)
        _isFriendAdded.value = result
        // 친구 프로필을 다시 로드하여 상태 업데이트
        loadFriendProfile(friendId)
    }

    fun acceptFriend(friendId: String) {
        friendRepository.updateFriendStatus(friendId, "accepted")
        // 친구 프로필을 다시 로드하여 상태 업데이트
        loadFriendProfile(friendId)
    }
}
