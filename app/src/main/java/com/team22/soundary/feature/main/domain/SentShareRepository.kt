package com.team22.soundary.feature.main.domain

import com.team22.soundary.core.domain.model.Share
import kotlinx.coroutines.flow.Flow

interface SentShareRepository {
    suspend fun getShareList() : Flow<List<Share>>
}