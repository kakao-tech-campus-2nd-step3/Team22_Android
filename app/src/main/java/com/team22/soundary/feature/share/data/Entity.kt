package com.team22.soundary.feature.share.data

data class ErrorResponse(
    val code: String,
    val message: String
)

data class SearchTrackResponse(
    val tracks: List<TrackResponseDto>
)

data class TrackResponseDto(
    val platform: String,
    val platform_track_id: String,
    val title: String,
    val artists: List<String>,
    val duration: Int,
    val album_cover_url: String?,
    val preview_mp3_url: String
)