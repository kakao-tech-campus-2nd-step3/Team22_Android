package com.team22.soundary.feature.main

import android.net.Uri
import com.team22.soundary.R

data class MainUiState(
    val friendNameList: List<String> = emptyList(),
    val friendName: String = "",
    val friendImage: Uri? = null,
    val isLastSong: Boolean = true,
    val isFirstSong: Boolean = true,
    val songUri: Uri = Uri.EMPTY,
    val songImage: Uri = Uri.EMPTY,
    val musicName: String = "",
    val singer: String = "",
    val message: String = "",
    val likeBackground: Int = R.drawable.main_like_background
)
