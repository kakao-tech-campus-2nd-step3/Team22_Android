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
            SongEntity("스토커", "10cm", "", "", "", Date()),
            "이거듣고 눈물흘림",
            false
        ),
        ShareEntity(
            UserEntity("", "춘식이", "", "", "", Date(), Date()),
            SongEntity("Celebrity", "IU", "", "", "", Date()),
            "좋은 노래네요",
            false
        ),
        ShareEntity(
            UserEntity("", "라이언", "", "", "", Date(), Date()),
            SongEntity("Dynamite", "BTS", "", "", "", Date()),
            "신나는 노래!",
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
    return Share(
        this.song?.toVO(),
        this.message ?: "",
        this.sentUser?.toVO(),
        this.isLike ?: false
    )
}

fun SongEntity.toVO(): Song {
    return Song(
        this.title ?: "",
        this.artist ?: "",
        this.preview ?: "",
        this.coverImage ?: ""

    )
}

fun UserEntity.toVO(): User {
    return User(
        this.nickname ?: "",
        this.image ?: ""
    )
}