package com.ewide.test.faris_ghilmany.core.data

import androidx.paging.PagingData
import com.ewide.test.faris_ghilmany.core.data.source.local.LocalDataSource
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.data.source.paging.PagingDataSource
import com.ewide.test.faris_ghilmany.core.data.source.remote.RemoteDataSource
import com.ewide.test.faris_ghilmany.core.domain.repository.IGameRepository
import com.ewide.test.faris_ghilmany.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val pagingDataSource: PagingDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {
    override fun getGame(): Flow<PagingData<GameEntity>> {
        return pagingDataSource.getStories()
    }
}