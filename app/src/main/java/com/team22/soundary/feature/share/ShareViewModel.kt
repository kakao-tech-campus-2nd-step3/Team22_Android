package com.team22.soundary.feature.share

import androidx.lifecycle.ViewModel
import com.team22.soundary.feature.main.domain.GetShareUseCase
import com.team22.soundary.feature.share.domain.Friend
import com.team22.soundary.feature.share.domain.MusicRepository
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

    private fun setFriendItemList(updatedList: List<Friend>) {
        _friendList.value = updatedList
    }

    private fun setSelectItemList(updatedList: List<Friend>) {
        _selectList.value = updatedList
    }

    fun setComment(updatedText: String) {
        _comment.value = updatedText
    }

    init {
        init()
    }

    private fun init() { // 나중에 백엔드에서 가져오도록 수정해야하는 친구 정보
        val initList = mutableListOf<Friend>()
        for (i in 0..10) {
            initList.add(Friend("" + i, "쿠키즈", null, false))
        }
        for (i in 11..19) {
            initList.add(Friend("" + i, "쿠키즈", "imageSrc", false))
        }
        setFriendItemList(initList)
    }

    fun setItemVisibility(selectItem: Friend) {
        val updatedList = _friendList.value.map {
            if (it.id == selectItem.id) {
                it.copy(isSelected = !it.isSelected)
            } else {
                it
            }
        }
        setFriendItemList(updatedList)
        updateSelectItemList()
    }

    fun setItemVisibilityAll(visibility: Boolean) {
        val updatedList = _friendList.value.map {
            it.copy(isSelected = visibility)
        }
        setFriendItemList(updatedList)
        updateSelectItemList()
    }

    fun isSelectedAll(): Boolean {
        return _selectList.value.size == _friendList.value.size
    }

    fun updateSelectItemList() {
        val selectList = mutableListOf<Friend>()
        for (item in _friendList.value) {
            if (item.isSelected) {
                selectList.add(item)
            }
        }
        setSelectItemList(selectList)
    }

    fun getButtonText(): String {
        return "" + _selectList.value.size + "명에게 보내기"
    }
}