package com.team22.soundary.feature.main.domain

import android.util.Log
import dagger.Component
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetShareUseCase @Inject constructor(
    private val shareRepository: ShareRepository
) {
    fun invoke(): Flow<Map<String, List<Share>>> {
        return shareRepository.getShareList().map { shareList ->
            shareList.groupBy { it.friend.name }
        }
    }

}