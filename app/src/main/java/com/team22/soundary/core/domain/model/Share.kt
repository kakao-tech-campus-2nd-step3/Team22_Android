package com.team22.soundary.core.domain.model

data class Share(
    val song: Song = Song(),
    val message: String = "",
    val friend: User = User(),
    val isLike: Boolean = false
)
