package com.team22.soundary.feature.main.data.remote

import com.team22.soundary.core.data.dto.ReceivedShareListDto
import com.team22.soundary.core.data.dto.SentShareListDto
import com.team22.soundary.core.data.dto.UserInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/api/v1/user")
    suspend fun requestMyInfo(): Response<UserInfoDto>

}