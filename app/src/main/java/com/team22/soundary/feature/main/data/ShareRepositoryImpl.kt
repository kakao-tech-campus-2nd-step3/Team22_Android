package com.team22.soundary.feature.main.data

import android.net.Uri
import com.team22.soundary.feature.main.domain.Share
import com.team22.soundary.feature.main.domain.ShareRepository
import com.team22.soundary.feature.main.domain.Song
import com.team22.soundary.feature.main.domain.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

class ShareRepositoryImpl : ShareRepository {
    private var mockDataList: MutableList<ShareEntity> = mutableListOf(
        ShareEntity(
            UserEntity("", "춘식이", "", "", "", Date(), Date()),
            SongEntity("", "스토커", "", "", "10cm", Date()),
            "이거듣고 눈물흘림",
            false
        ),
        ShareEntity(
            UserEntity("", "춘식이", "", "", "", Date(), Date()),
            SongEntity("", "Celebrity", "", "", "IU", Date()),
            "좋은 노래네요",
            false
        ),
        ShareEntity(
            UserEntity("", "라이언", "", "", "", Date(), Date()),
            SongEntity("", "Dynamite", "", "", "BTS", Date()),
            "신나는 노래 추천!",
            false
        ),
        ShareEntity(
            UserEntity("", "춘식이", "", "", "", Date(), Date()),
            SongEntity("", "Tokyo Flash", "", "", "Vaundy", Date()),
            "노래입니다.",
            false
        ),
        ShareEntity(
            UserEntity("", "콘", "", "", "", Date(), Date()),
            SongEntity("", "Supernova", "", "", "aespa", Date()),
            "내 최애 노래야!",
            false
        ),
    )


    override fun getShareList(): Flow<List<Share>> = flow {
        emit(
            mockDataList.map { it.toVO() }
        )
    }
}

fun ShareEntity.toVO(): Share {
    val onNull = "unknown"
    return Share(
        this.song?.toVO() ?: Song(onNull,onNull,onNull,onNull),
        this.message ?: onNull,
        this.sentUser?.toVO() ?: User(onNull,onNull),
        this.isLike ?: false
    )
}

fun SongEntity.toVO(): Song {
    val onNull = "unknown"
    return Song(
        this.title ?: onNull,
        this.artist ?: onNull,
        this.preview ?: onNull,
        this.coverImage ?: onNull

    )
}

fun UserEntity.toVO(): User {
    val onNull = "unknown"
    return User(
        this.nickname ?: onNull,
        this.image ?: onNull
    )
}

