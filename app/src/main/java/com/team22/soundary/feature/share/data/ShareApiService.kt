package com.team22.soundary.feature.share.data

import com.team22.soundary.feature.share.data.SearchTrackResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ShareApiService {
    @GET("/api/v1/tracks")
    fun requestMusicList(
        @Query("platform") platform: String = "SPOTIFY",
        @Query("query") query: String
    ): Call<SearchTrackResponse>
}