package com.ewide.test.faris_ghilmany.core.data.source.remote

import com.ewide.test.faris_ghilmany.core.data.source.remote.network.ApiResponse
import com.ewide.test.faris_ghilmany.core.data.source.remote.network.GameApiService
import com.ewide.test.faris_ghilmany.core.data.source.remote.response.DetailGameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteDataSource(private val gameApiService: GameApiService?) {
    suspend fun getDetailGame(id: String): Flow<ApiResponse<DetailGameResponse>> {
        return flow {
            try {
                val response = gameApiService?.getDetailDeals(id)
                if (response != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}