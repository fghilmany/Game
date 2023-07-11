package com.ewide.test.faris_ghilmany.core.data

import com.ewide.test.faris_ghilmany.core.data.source.local.LocalDataSource
import com.ewide.test.faris_ghilmany.core.data.source.remote.RemoteDataSource
import com.ewide.test.faris_ghilmany.core.domain.repository.IGameRepository
import com.ewide.test.faris_ghilmany.core.utils.AppExecutors

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {
}