package com.team22.soundary.feature.main

import android.graphics.Bitmap

data class MainUiState(
    val friendNameList: List<String> = emptyList(),
    val friendName: String = "",
    val friendImage: String? = null,
    val isLastSong: Boolean = true,
    val isFirstSong: Boolean = true,
    val musicName: String = "",
    val singer: String = "",
    val message: String = "",
    val isLikeSong: Boolean = false
)
