package com.team22.soundary.feature.search

import androidx.lifecycle.ViewModel
import com.team22.soundary.core.domain.model.User
import com.team22.soundary.feature.search.data.repository.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FriendSearchViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {

    private val _newFriends = MutableStateFlow<List<User>>(emptyList())
    val newFriends: StateFlow<List<User>> get() = _newFriends.asStateFlow()

    private val _myFriends = MutableStateFlow<List<User>>(emptyList())
    val myFriends: StateFlow<List<User>> get() = _myFriends.asStateFlow()

    private val _pendingFriends = MutableStateFlow<List<User>>(emptyList())
    val pendingFriends: StateFlow<List<User>> get() = _pendingFriends.asStateFlow()

    init {
        loadFriends()
    }

    private fun loadFriends() {
        _newFriends.value = friendRepository.getNewFriends()
        _myFriends.value = friendRepository.getMyFriends()
        _pendingFriends.value = friendRepository.getPendingFriends()
    }

    // 친구 수락 메서드 추가
    fun acceptFriend(friend: User) {
        friendRepository.updateFriendStatus(friend.id, "accepted")
        loadFriends()
    }

    // 친구 거절 메서드 추가
    fun declineFriend(friend: User) {
        friendRepository.removeFriend(friend.id)
        loadFriends()
    }

    // 친구 삭제 메서드 추가
    fun deleteFriend(friend: User) {
        friendRepository.removeFriend(friend.id)
        loadFriends()
    }

    fun filterFriends(query: String) {
        val filteredNewFriends = friendRepository.getNewFriends().filter {
            it.name.contains(query, ignoreCase = true)
        }
        val filteredMyFriends = friendRepository.getMyFriends().filter {
            it.name.contains(query, ignoreCase = true)
        }
        val filteredPendingFriends = friendRepository.getPendingFriends().filter {
            it.name.contains(query, ignoreCase = true)
        }

        _newFriends.value = filteredNewFriends
        _myFriends.value = filteredMyFriends
        _pendingFriends.value = filteredPendingFriends
    }

    fun resetFilters() {
        loadFriends()
    }
}
