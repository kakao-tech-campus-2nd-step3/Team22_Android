package com.team22.soundary.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team22.soundary.feature.main.domain.GetShareUseCase
import com.team22.soundary.feature.main.domain.Share
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getShareUseCase: GetShareUseCase = GetShareUseCase()
) : ViewModel() {
    private var groupedShares: Map<String, List<Share>> = emptyMap()
    val friendNames: List<String>
        get() = groupedShares.keys.toList() //for setting spinner
    private var _currentFriendIndex = 0
    private var _currentSongIndex = 0

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getShareUseCase.invoke().collect{
                groupedShares = it
                updateUiState()
            }
        }
    }


    fun onFriendChanged(index: Int) {
        _currentFriendIndex = index
        _currentSongIndex = 0
        updateUiState()
    }

    fun onNextClicked() {
        if (_currentSongIndex < getCurrentShareList().size - 1) {
            _currentSongIndex += 1
            updateUiState()
        }
    }

    fun onPrevClicked() {
        if (_currentSongIndex > 0) {
            _currentSongIndex -= 1
            updateUiState()
        }
    }

    private fun getCurrentShareList(): List<Share> {
        val currentFriendName = friendNames[_currentFriendIndex]
        return groupedShares[currentFriendName] ?: emptyList()
    }

    private fun updateUiState() {
        val currentFriendShares = getCurrentShareList()
        if (currentFriendShares.isNotEmpty()) {
            val currentShare = currentFriendShares[_currentSongIndex]
            _uiState.update {
                MainUiState(
                    friendNameList = groupedShares.keys.toList(),
                    friendName = currentShare.friend.name,
                    friendImage = currentShare.friend.image,
                    musicName = currentShare.song.title,
                    singer = currentShare.song.artist,
                    message = currentShare.message,
                    isLastSong = _currentSongIndex == currentFriendShares.size - 1,
                    isFirstSong = _currentSongIndex == 0,
                    isLikeSong = currentShare.isLike
                )
            }
        }
    }
}