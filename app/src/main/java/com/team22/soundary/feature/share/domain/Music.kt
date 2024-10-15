package com.team22.soundary.feature.share.domain

data class Music(
    val platform: String,
    val platformTrackId: String,
    val title: String,
    val artists: List<String>,
    val albumCoverUrl: String?,
    //val id: Int,
    //val music: String,
    //val singer: String,
    //val sortValue: String
)