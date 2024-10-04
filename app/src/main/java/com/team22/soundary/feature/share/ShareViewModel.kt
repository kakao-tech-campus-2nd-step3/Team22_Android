package com.team22.soundary.feature.share

import androidx.lifecycle.ViewModel
import com.team22.soundary.feature.share.data.FriendItemEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShareViewModel : ViewModel() {
    private val _friendList = MutableStateFlow<List<FriendItemEntity>>(emptyList())
    val friendList: StateFlow<List<FriendItemEntity>> = _friendList.asStateFlow()

    private val _selectList = MutableStateFlow<List<FriendItemEntity>>(emptyList())
    val selectList: StateFlow<List<FriendItemEntity>> = _selectList.asStateFlow()

    private val _comment = MutableStateFlow("")
    val comment: StateFlow<String> = _comment.asStateFlow()

    private fun setFriendItemList(updatedList: List<FriendItemEntity>) {
        _friendList.value = updatedList
    }

    private fun setSelectItemList(updatedList: List<FriendItemEntity>) {
        _selectList.value = updatedList
    }

    fun setComment(updatedText: String) {
        _comment.value = updatedText
    }

    fun init() { // 나중에 백엔드에서 가져오도록 수정해야하는 친구 정보
        val initList = mutableListOf<FriendItemEntity>()
        for (i in 0..10) {
            initList.add(FriendItemEntity("" + i, "쿠키즈", null, false))
        }
        for (i in 11..19) {
            initList.add(FriendItemEntity("" + i, "쿠키즈", "imageSrc", false))
        }
        setFriendItemList(initList)
    }

    fun setItemVisibility(selectItem: FriendItemEntity) {
        val updatedList = _friendList.value.map {
            if (it.id == selectItem.id) {
                it.copy(isSelected = !it.isSelected)
            } else {
                it
            }
        }
        setFriendItemList(updatedList)
    }

    fun updateSelectItemList(){
        val selectList = mutableListOf<FriendItemEntity>()
        for (item in _friendList.value) {
            if (item.isSelected) {
                selectList.add(item)
            }
        }
        setSelectItemList(selectList)
    }
}