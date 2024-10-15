package com.team22.soundary.feature.share.data

import android.util.Log
import com.team22.soundary.feature.share.domain.Music
import com.team22.soundary.feature.share.domain.MusicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val shareApiService: ShareApiService
) : MusicRepository {
    lateinit var musicList: SearchTrackResponse

    private var musicListTemp: List<TrackResponseDto> = mutableListOf(
        TrackResponseDto("SPOTIFY", "1", "제목", listOf("가수"), 1, null, "mp3"),
        TrackResponseDto("SPOTIFY", "2", "제목", listOf("가수"), 1, null, "mp3"),
        TrackResponseDto("SPOTIFY", "3", "제목", listOf("가수1", "가수2"), 1, null, "mp3"),
        TrackResponseDto("SPOTIFY", "4", "제목", listOf("가수"), 1, null, "mp3"),
        TrackResponseDto("SPOTIFY", "5", "제목", listOf("가수"), 1, null, "mp3")
    )

    suspend fun searchMusicItem(query: String) {
        val response = withContext(Dispatchers.IO) {
            shareApiService.requestMusicList(query = query).execute()
        }
        if (response.isSuccessful) {
            musicList = response.body() ?: SearchTrackResponse(emptyList())
        }
    }

    //override fun getMusicList(): Flow<List<Music>?> = flowOf(musicList?.tracks?.map{it.toVO()})

    override fun getMusicList(): Flow<List<Music>> = flowOf(musicListTemp.map {
        it.toVO()
    })
}

fun TrackResponseDto.toVO(): Music {
    return Music(
        this.platform,
        this.platformTrackId,
        this.title,
        this.artists,
        this.albumCoverUrl
    )
}