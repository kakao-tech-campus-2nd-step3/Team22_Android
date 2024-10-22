package com.team22.soundary.feature.main.data

import android.net.Uri
import android.util.Log
import com.team22.soundary.core.data.dto.toVO
import com.team22.soundary.core.domain.model.Share
import com.team22.soundary.core.domain.model.Song
import com.team22.soundary.core.domain.model.User
import com.team22.soundary.feature.main.data.remote.ShareService
import com.team22.soundary.feature.main.domain.ReceivedShareRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReceivedShareRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val retrofitService: ShareService
) : ReceivedShareRepository{

    override suspend fun getShareList(): Flow<List<Share>> = flow {
        val response = withContext(dispatcher) {
            retrofitService.requestReceiveShare()
        }

        if(response.isSuccessful){
            response.body()?.shareList?.map{
                it.toVO()
            }?.let{
                emit(it)
            }
        } else {
            Log.e("dd",response.message())
        }
    }
}

