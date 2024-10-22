package com.team22.soundary.feature.share.domain

import com.team22.soundary.core.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getMusicList(): Flow<List<Song>>
}