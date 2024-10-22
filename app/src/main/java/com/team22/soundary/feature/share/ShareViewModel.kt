package com.team22.soundary.feature.share

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.team22.soundary.core.domain.model.Category
import com.team22.soundary.core.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
) : ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList: StateFlow<List<User>> = _userList.asStateFlow()

    private val _filteredUserList = MutableStateFlow<List<User>>(emptyList())
    val filteredUserList: StateFlow<List<User>> = _filteredUserList.asStateFlow()

    private val _selectedFriendIds = MutableStateFlow<Set<String>>(emptySet())
    val selectedFriendIds: StateFlow<Set<String>> = _selectedFriendIds.asStateFlow()

    private val _comment = MutableStateFlow("")
    val comment: StateFlow<String> = _comment.asStateFlow()

    private val _category = MutableStateFlow<Category?>(null)

    init {
        initFriendList()
    }

    fun setComment(updatedText: String) {
        _comment.value = updatedText
    }

    private fun initFriendList() {
        val initList = mutableListOf<User>()
        for (i in 0..5) {
            initList.add(User(id="$i", name="댄스", image= Uri.EMPTY, category = listOf(Category.Dance)))
        }
        for (i in 6..10) {
            initList.add(User(id="$i", name="힙합", image= Uri.EMPTY, category = listOf(Category.Hiphop)))
        }
        for (i in 11..19) {
            initList.add(User(id="$i", name="쿠키즈", image= Uri.EMPTY, category = listOf(Category.RnB)))
        }
        _userList.value = initList
        getFilteredFriendList(_category.value)
    }

    fun toggleFriendSelection(friendId: String) {
        _selectedFriendIds.update { currentSelectedIds ->
            if (currentSelectedIds.contains(friendId)) {
                currentSelectedIds - friendId
            } else {
                currentSelectedIds + friendId
            }
        }
    }

    fun setAllFriendsSelected(selected: Boolean) {
        if (selected) {
            _selectedFriendIds.value = _filteredUserList.value.map { it.id }.toSet()
        } else {
            _selectedFriendIds.value = emptySet()
        }
    }

    fun getFilteredFriendList(category: Category?) {
        _category.value = category
        _filteredUserList.value = if (category == null) {
            _userList.value
        } else {
            _userList.value.filter { it.category.contains(category) }
        }
    }

    fun isAllFriendsSelected(): Boolean {
        return _selectedFriendIds.value.size == _filteredUserList.value.size
    }

    fun getButtonText(): String {
        return "${_selectedFriendIds.value.size}명에게 보내기"
    }

    fun getSelectedFriends(): List<User> {
        return _userList.value.filter { _selectedFriendIds.value.contains(it.id) }
    }
}