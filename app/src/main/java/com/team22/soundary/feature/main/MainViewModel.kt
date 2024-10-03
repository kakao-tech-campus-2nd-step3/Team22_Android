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

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getShareUseCase: GetShareUseCase
) : ViewModel() {
    private var groupedShares: Map<String, List<Share>> = emptyMap()
    val friendNames: List<String>
        get() = groupedShares.keys.toList() //for setting spinner
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getShareUseCase.invoke().collect {
                groupedShares = it
                if (it.isNotEmpty()) updateUiState(it.entries.first().value.first(), 0)
            }
        }
    }


    fun onFriendChanged(index: Int) {
        val targetFriendName = friendNames[index]
        groupedShares[targetFriendName]?.first()?.let { updateUiState(it, 0) }
    }

    fun onNextClicked() {
        val currentIndex = getCurrentShareIndex()
        val nextIndex = currentIndex + 1

        if (currentIndex == EMPTY_SHARE || nextIndex == getCurrentShares().size) return
        updateUiState(getCurrentShares()[nextIndex], nextIndex)
    }

    fun onPrevClicked() {
        val currentIndex = getCurrentShareIndex()
        val prevIndex = currentIndex - 1

        if (currentIndex == EMPTY_SHARE || prevIndex < 0) return
        updateUiState(getCurrentShares()[prevIndex], prevIndex)
    }

    private fun getCurrentShares(): List<Share> =
        groupedShares[_uiState.value.friendName] ?: emptyList()

    private fun getCurrentShareIndex(): Int {
        val currentFriendName = _uiState.value.friendName
        val currentSongTitle = _uiState.value.musicName
        val shares = groupedShares[currentFriendName]

        return shares?.indexOfFirst { it.song.title == currentSongTitle } ?: EMPTY_SHARE
    }

    private fun updateUiState(targetShare: Share, shareIndex: Int) {
        _uiState.update {
            MainUiState(
                friendName = targetShare.friend.name,
                friendImage = targetShare.friend.image,
                musicName = targetShare.song.title,
                singer = targetShare.song.artist,
                message = targetShare.message,
                isLastSong = shareIndex == groupedShares[targetShare.friend.name]?.size?.minus(1),
                isFirstSong = shareIndex == 0,
                isLikeSong = targetShare.isLike
            )
        }

    }

    companion object {
        const val EMPTY_SHARE = -1
    }
}