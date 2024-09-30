package com.team22.soundary.feature.main.domain

data class Share(
    val song: Song,
    val message: String,
    val friend: User,
    val isLike: Boolean
)