package com.team22.soundary.core.model

import android.net.Uri

data class Song(
    val id: String = "",
    val title: String = "",
    val artist: List<String> = emptyList(),
    val preview: Uri = Uri.EMPTY,
    val coverImage: Uri = Uri.EMPTY
)
