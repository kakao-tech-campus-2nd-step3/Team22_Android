package com.team22.soundary.feature.main.data

import java.util.Date

data class UserEntity(
    val id: String,
    val nickname : String?,
    val displayId : String?,
    val image : String?,
    val description : String?,
    val createDate : Date?,
    val updateDate : Date?
)

data class SongEntity(
    val id: String,
    val title: String?,
    val coverImage: String?,
    val preview : String?,
    val artist : String?,
    val createDate : Date?
)

data class ShareEntity(
    val sentUser: UserEntity?,
    val song : SongEntity?,
    val message: String?,
    val isLike : Boolean?
)