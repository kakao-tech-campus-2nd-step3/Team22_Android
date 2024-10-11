package com.team22.soundary.feature.share.domain

data class Music(
    val platform: String,
    val platform_track_id: String,
    val title: String,
    val artists: List<String>,
    val album_cover_url: String?,
    //val id: Int,
    //val music: String,
    //val singer: String,
    //val sortValue: String
)