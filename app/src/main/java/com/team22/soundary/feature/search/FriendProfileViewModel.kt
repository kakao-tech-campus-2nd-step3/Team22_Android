package com.team22.soundary.feature.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team22.soundary.feature.search.data.model.FriendEntity
import com.team22.soundary.feature.search.data.repository.FriendRepository

class FriendProfileViewModel : ViewModel() {

    private val friendRepository = FriendRepository()

    private val _friendProfile = MutableLiveData<FriendEntity>()
    val friendProfile: LiveData<FriendEntity> get() = _friendProfile

    fun loadFriendProfile(friendId: String) {
        val profile = friendRepository.getFriendById(friendId)
        profile?.let {
            _friendProfile.value = it
        }
    }
}
