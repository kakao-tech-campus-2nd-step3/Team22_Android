package com.team22.soundary.feature.main.domain

import com.team22.soundary.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getMyInfo() : Flow<User>
}