package com.team22.soundary.feature.main.data

import android.net.Uri
import java.util.Date

data class UserEntity(
    val id: String,
    val nickname : String?,
    val displayId : String?,
    val image : Uri?,
    val description : String?,
    val createDate : Date?,
    val updateDate : Date?
)

data class SongEntity(
    val id: String,
    val title: String?,
    val coverImage: Uri?,
    val preview : Uri?,
    val artist : String?,
    val createDate : Date?
)

data class ShareEntity(
    val sentUser: UserEntity?,
    val song : SongEntity?,
    val message: String?,
    val isLike : Boolean?
)