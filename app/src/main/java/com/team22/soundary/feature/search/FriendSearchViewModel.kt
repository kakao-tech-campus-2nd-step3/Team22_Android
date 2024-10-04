package com.team22.soundary.feature.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team22.soundary.feature.search.data.model.FriendEntity
import com.team22.soundary.feature.search.data.repository.FriendRepository

class FriendSearchViewModel : ViewModel() {

    private val friendRepository = FriendRepository()

    private val _newFriends = MutableLiveData<List<FriendEntity>>()
    val newFriends: LiveData<List<FriendEntity>> get() = _newFriends

    private val _myFriends = MutableLiveData<List<FriendEntity>>()
    val myFriends: LiveData<List<FriendEntity>> get() = _myFriends

    private val _pendingFriends = MutableLiveData<List<FriendEntity>>()
    val pendingFriends: LiveData<List<FriendEntity>> get() = _pendingFriends

    init {
        loadFriends()
    }

    private fun loadFriends() {
        _newFriends.value = friendRepository.getNewFriends()
        _myFriends.value = friendRepository.getMyFriends()
        _pendingFriends.value = friendRepository.getPendingFriends()
    }

    // 친구 수락 메서드 추가
    fun acceptFriend(friend: FriendEntity) {
        friendRepository.updateFriendStatus(friend.id, "accepted")
        loadFriends()
    }

    // 친구 거절 메서드 추가
    fun declineFriend(friend: FriendEntity) {
        friendRepository.removeFriend(friend.id)
        loadFriends()
    }

    // 친구 삭제 메서드 추가
    fun deleteFriend(friend: FriendEntity) {
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
