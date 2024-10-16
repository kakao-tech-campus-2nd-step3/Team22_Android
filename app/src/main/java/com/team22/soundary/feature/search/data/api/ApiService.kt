package com.team22.soundary.feature.search.data.api

import com.team22.soundary.feature.search.data.model.FriendEntity
import retrofit2.http.GET

interface ApiService {
    @GET("friends")// 나중에 엔드포인트 수정하기
    suspend fun getFriends(): List<FriendEntity>
}
