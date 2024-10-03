package com.team22.soundary.feature.main.domain

import com.team22.soundary.feature.main.data.ShareEntity
import kotlinx.coroutines.flow.Flow

interface ShareRepository {
    fun getShareList() : Flow<List<Share>>
}