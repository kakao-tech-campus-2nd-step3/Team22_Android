package com.team22.soundary.feature.share.data

import com.team22.soundary.feature.share.domain.Music
import com.team22.soundary.feature.share.domain.MusicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(private val shareApiService: ShareApiService) :
    MusicRepository {
    private var musicList: SearchTrackResponse? = null

    private var musicListTemp: List<TrackResponseDto> = mutableListOf(
        TrackResponseDto("SPOTIFY", "1", "제목", listOf("가수"), 1, null, "mp3"),
        TrackResponseDto("SPOTIFY", "2", "제목", listOf("가수"), 1, null, "mp3"),
        TrackResponseDto("SPOTIFY", "3", "제목", listOf("가수1", "가수2"), 1, null, "mp3"),
        TrackResponseDto("SPOTIFY", "4", "제목", listOf("가수"), 1, null, "mp3"),
        TrackResponseDto("SPOTIFY", "5", "제목", listOf("가수"), 1, null, "mp3"),
    )

    suspend fun searchMusicItem(query: String) {
        val responseEachPage = withContext(Dispatchers.IO) {
            shareApiService.requestMusicList(query = query).execute()
        }
        if (responseEachPage.isSuccessful) {
            musicList = responseEachPage.body()
        }
    }

    //override fun getMusicList(): Flow<List<Music>?> = flowOf(musicList?.tracks?.map{it.toVO()})

    override fun getMusicList(): Flow<List<Music>?> = flowOf(musicListTemp.map { it.toVO() })
}

fun TrackResponseDto.toVO(): Music {
    return Music(
        this.platform,
        this.platform_track_id,
        this.title,
        this.artists,
        this.album_cover_url
    )
}