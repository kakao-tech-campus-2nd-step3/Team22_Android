package com.team22.soundary.feature.share

import android.util.Log
import androidx.lifecycle.ViewModel
import com.team22.soundary.feature.share.domain.Friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
): ViewModel() {
    private val _friendList = MutableStateFlow<List<Friend>>(emptyList())
    val friendList: StateFlow<List<Friend>> = _friendList.asStateFlow()

    private val _selectList = MutableStateFlow<List<Friend>>(emptyList())
    val selectList: StateFlow<List<Friend>> = _selectList.asStateFlow()

    private val _comment = MutableStateFlow("")
    val comment: StateFlow<String> = _comment.asStateFlow()

//    private fun setFriendItemList(updatedList: List<Friend>) {
//        _friendList.value = updatedList
//    }
//
//    private fun setSelectItemList(updatedList: List<Friend>) {
//        _selectList.value = updatedList
//    }

    private val _category = MutableStateFlow<Category?>(null)


    fun setComment(updatedText: String) {
        _comment.value = updatedText
    }

    init {
        init()
    }

    private fun init() { // 나중에 백엔드에서 가져오도록 수정해야하는 친구 정보
        val initList = mutableListOf<Friend>()
        for (i in 0..5) {
            initList.add(Friend("" + i, "댄스", null, listOf(Category.dance), false))
        }
        for (i in 6..10) {
            initList.add(Friend("" + i, "힙합", null, listOf(Category.hiphop), false))
        }
        for (i in 11..19) {
            initList.add(Friend("" + i, "쿠키즈", "imageSrc", listOf(Category.RnB), false))
        }
        _friendList.value = initList
        getFilteredFriendList(_category.value)
    }

    fun setItemSelected(selectItem: FriendItemEntity) {
        _friendList.update { list ->
            list.map {
                if (it.id == selectItem.id) {
                    it.copy(isSelected = !it.isSelected)
                } else {
                    it
                }
            }
        }
        getFilteredFriendList(_category.value)
        updateSelectItemList()
    }

    fun setItemSelectedAll(visibility: Boolean) {
        _friendList.update { list ->
            list.map {
                it.copy(isSelected = visibility)
            }
        }
        getFilteredFriendList(_category.value)
        updateSelectItemList()
    }

    private fun updateSelectItemList() {
        _selectList.value = _friendList.value.filter { it.isSelected }
    }

    fun getFilteredFriendList(category: Category?) {
        _category.value = category
        if (_category.value == null) {
            _filteredFriendList.value = _friendList.value
        } else {
            _filteredFriendList.value =
                _friendList.value.filter { it.category.contains(_category.value) }
        }
    }

    fun isSelectedAll(): Boolean {
        return _selectList.value.size == _friendList.value.size
    }

    fun getButtonText(): String {
        return "" + _selectList.value.size + "명에게 보내기"
    }
}