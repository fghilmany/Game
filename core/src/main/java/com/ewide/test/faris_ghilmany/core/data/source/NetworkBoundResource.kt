package com.ewide.test.faris_ghilmany.core.data.source

import com.ewide.test.faris_ghilmany.core.data.Resource
import com.ewide.test.faris_ghilmany.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import timber.log.Timber

abstract class NetworkBoundResource<RequestType> {
    private var result: Flow<Resource<RequestType>> = flow {

        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                getResponse(apiResponse.data)
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty -> {}
            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
                Timber.e(apiResponse.errorMessage)
            }
        }

    }

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun getResponse(data: RequestType)

    fun asFlow(): Flow<Resource<RequestType>> = result
}