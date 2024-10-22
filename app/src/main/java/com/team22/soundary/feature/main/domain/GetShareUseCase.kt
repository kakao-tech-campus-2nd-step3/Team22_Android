package com.team22.soundary.feature.main.domain

import android.util.Log
import com.team22.soundary.core.domain.model.Share
import dagger.Component
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetShareUseCase @Inject constructor(
    private val sentShareRepository: SentShareRepository,
    private val receivedShareRepository: ReceivedShareRepository,
    private val userRepository: UserRepository
) {
    suspend fun invoke(): Flow<Map<String, List<Share>>> =
        combine(
            sentShareRepository.getShareList(),
            receivedShareRepository.getShareList(),
            userRepository.getMyInfo()
        ) { sent, receive, me ->
            sent.map{
                it.copy(
                    friend = me
                )
            }
            sent + receive
        }.map { shareList ->
            shareList.groupBy { it.friend.name }
        }
}