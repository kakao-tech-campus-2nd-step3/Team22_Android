package com.team22.soundary.feature.main.data.remote

import com.team22.soundary.core.data.dto.ReceivedShareListDto
import com.team22.soundary.core.data.dto.SentShareListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ShareService {
    @GET("/api/v1/shared-musics/sent")
    suspend fun requestSentShare(): Response<SentShareListDto>

    @GET("/api/v1/shared-musics/received")
    suspend fun requestReceiveShare(): Response<ReceivedShareListDto>
}