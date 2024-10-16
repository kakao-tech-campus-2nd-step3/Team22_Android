package com.team22.soundary.feature.share.domain

import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getMusicList(): Flow<List<Music>>
}