package com.team22.soundary.feature.main.domain

import com.team22.soundary.feature.main.data.ShareRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetShareUseCase(private val shareRepository: ShareRepository = ShareRepositoryImpl()) {

    fun invoke(): Flow<Map<String, List<Share>>> {
        return shareRepository.getShareList().map { shareList ->
            shareList.groupBy { it.friend.name }
        }
    }

}