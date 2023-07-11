package com.ewide.test.faris_ghilmany.core.data

import com.ewide.test.faris_ghilmany.core.data.source.local.LocalDataSource
import com.ewide.test.faris_ghilmany.core.data.source.remote.RemoteDataSource
import com.ewide.test.faris_ghilmany.core.domain.repository.IDisneyRepository
import com.ewide.test.faris_ghilmany.core.utils.AppExecutors

class DisneyRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IDisneyRepository {
}