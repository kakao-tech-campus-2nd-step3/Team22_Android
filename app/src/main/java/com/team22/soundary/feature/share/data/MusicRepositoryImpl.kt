package com.team22.soundary.feature.share.data

import android.net.Uri
import com.team22.soundary.core.dto.TrackDto
import com.team22.soundary.core.dto.TrackListDto
import com.team22.soundary.core.model.Song
import com.team22.soundary.feature.share.domain.MusicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val shareApiService: ShareService
) : MusicRepository {
    lateinit var musicList: TrackListDto

    private var musicListTemp: List<TrackDto> = mutableListOf(
        TrackDto("SPOTIFY", "1", "제목", listOf("가수"), 1, null, "mp3"),
        TrackDto("SPOTIFY", "2", "제목", listOf("가수"), 1, null, "mp3"),
        TrackDto("SPOTIFY", "3", "제목", listOf("가수1", "가수2"), 1, null, "mp3"),
        TrackDto("SPOTIFY", "4", "제목", listOf("가수"), 1, null, "mp3"),
        TrackDto("SPOTIFY", "5", "제목", listOf("가수"), 1, null, "mp3")
    )

    suspend fun searchMusicItem(query: String) {
        val response = withContext(Dispatchers.IO) {
            shareApiService.requestMusicList(query = query).execute()
        }
        if (response.isSuccessful) {
            musicList = response.body() ?: TrackListDto(emptyList())
        }
    }

    //override fun getMusicList(): Flow<List<Music>?> = flowOf(musicList?.tracks?.map{it.toVO()})

    override fun getMusicList(): Flow<List<Song>> = flowOf(musicListTemp.map {
        it.toVO()
    })
}

fun TrackDto.toVO(): Song {
    return Song(
        this.platformTrackId ?: "",
        this.title ?: "",
        this.artist ?: emptyList(),
        Uri.parse("")
    )
}