package com.team22.soundary.feature.share.data

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    val code: String,
    val message: String
)

data class SearchTrackResponse(
    val tracks: List<TrackResponseDto>
)

data class TrackResponseDto(
    val platform: String,
    @SerializedName("platform_track_id")
    val platformTrackId: String,
    val title: String,
    val artists: List<String>,
    val duration: Int,
    @SerializedName("album_cover_url")
    val albumCoverUrl: String?,
    @SerializedName("preview_mp3_url")
    val previewMp3Url: String
)