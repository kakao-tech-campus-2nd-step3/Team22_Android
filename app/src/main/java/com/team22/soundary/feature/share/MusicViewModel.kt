package com.team22.soundary.feature.share

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team22.soundary.feature.share.domain.Music
import com.team22.soundary.feature.share.domain.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicRepository: MusicRepository
) : ViewModel() {
    private val _musicList = MutableStateFlow<List<Music>?>(emptyList())
    val musicList: StateFlow<List<Music>?> = _musicList.asStateFlow()

    init {
        viewModelScope.launch {
            musicRepository.getMusicList().collect {
                _musicList.value = it
            }
        }
    }

}