package com.team22.soundary.feature.main.domain

import android.net.Uri

data class Song(
    val title: String,
    val artist: String,
    val preview: Uri,
    val coverImage: Uri
)
