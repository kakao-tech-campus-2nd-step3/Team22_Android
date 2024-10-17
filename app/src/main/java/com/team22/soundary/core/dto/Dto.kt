package com.team22.soundary.core.dto

import android.net.Uri
import com.google.gson.annotations.SerializedName
import com.team22.soundary.core.model.Share
import com.team22.soundary.core.model.Song
import com.team22.soundary.core.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ReceivedShareListDto(
    @SerialName("shared_musics") val shareList: List<ReceivedShareDto>
)

@Serializable
data class SentShareListDto(
    @SerialName("shared_musics") val shareList: List<SentShareDto>
)

@Serializable
data class ReceivedShareDto(
    @SerialName("id") val id: String?,
    @SerialName("from_user") val fromUser: FromUserResponse?,
    @SerialName("track") val track: TrackDto?,
    @SerialName("comment") val comment: String?,
    @SerialName("shared_at") val sharedAt: String?
)

@Serializable
data class SentShareDto(
    @SerialName("id") val id: String?,
    @SerialName("track") val track: TrackDto?,
    @SerialName("comment") val comment: String?,
    @SerializedName("shared_at") val sharedAt: String?
)

@Serializable
data class FromUserResponse(
    @SerialName("id") val id: String?,
    @SerialName("display_name") val displayName: String?,
    @SerialName("name") val name: String?,
    @SerialName("profile_image_url") val profileImageUrl: String?
)

@Serializable
data class TrackListDto(
    @SerialName("tracks") val trackList : List<TrackDto>
)

@Serializable
data class TrackDto(
    @SerialName("platform_track_id") val platformTrackId: String?,
    @SerialName("platform") val platform: String?,
    @SerialName("title") val title: String?,
    @SerialName("artist") val artist: List<String>?,
    @SerialName("duration") val duration: Int?,
    @SerialName("album_cover_url") val albumCoverUrl: String?,
    @SerialName("preview_mp_url") val previewMp3Url: String?
)

@Serializable
data class ErrorResponse(
    @SerialName("code") val code: String,
    @SerialName("message") val message: String
)

fun SentShareDto.toVO(): Share {
    return Share(
        this.track?.toVO() ?: Song(),
        this.comment ?: "",
        User(),//me
        false
    )
}

fun ReceivedShareDto.toVO(): Share {
    return Share(
        this.track?.toVO() ?: Song(),
        this.comment ?: "",
        this.fromUser?.toVO() ?: User(),
        false
    )
}

fun TrackDto.toVO(): Song =
    Song(
        this.platformTrackId ?: "",
        this.title ?: "",
        this.artist ?: emptyList(),
        Uri.parse(this.previewMp3Url ?: ""),
        Uri.parse(this.albumCoverUrl ?: "")
    )

fun FromUserResponse.toVO(): User =
    User(
        name = this.displayName ?: "",
        image = Uri.parse(this.profileImageUrl ?: "")
    )



