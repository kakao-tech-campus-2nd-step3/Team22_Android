package com.team22.soundary.core.data.dto

import android.net.Uri
import com.team22.soundary.core.domain.model.Share
import com.team22.soundary.core.domain.model.Song
import com.team22.soundary.core.domain.model.Token
import com.team22.soundary.core.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDto(
    @SerialName("displayId") val id : String?,
    @SerialName("nickname") val name : String?,
    @SerialName("description") val description : String?,
    @SerialName("profileImageUrl") val profile : String?,
    @SerialName("roles") val roles : List<String>?
)

@Serializable
data class ReceivedShareListDto(
    @SerialName("shared_musics") val shareList: List<ReceivedShareDto>?
)

@Serializable
data class SentShareListDto(
    @SerialName("total") val total: Int?,
    @SerialName("total_pages") val totalPage : Int?,
    @SerialName("shared_musics") val shareList: List<SentShareDto>?
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
    @SerialName("shared_at") val sharedAt: String?
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
    @SerialName("tracks") val trackList : List<TrackDto>?
)

@Serializable
data class TrackDto(
    @SerialName("track_id") val platformTrackId: String?,
    @SerialName("title") val title: String?,
    @SerialName("artists") val artist: List<String>?,
    @SerialName("album_cover_url") val albumCoverUrl: String?,
    @SerialName("refreshToken") val refreshToken: String?,
    @SerialName("expiresIn") val expiresIn : Int?
)

@Serializable
data class ErrorResponse(
    @SerialName("code") val code: String?,
    @SerialName("message") val message: String?
)

@Serializable
data class LoginRequestDto(
    @SerialName("platform") val platform: String = "KAKAO",
    @SerialName("token") val token: String
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

fun UserInfoDto.toVO(): User =
    User(
        id = this.id ?: "",
        name = this.name ?: "",
        image = Uri.parse(this.profile),
        statusMessage = this.description ?: ""
    )

    )



