package com.ewide.test.faris_ghilmany.core.data.source.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.data.source.local.room.GameDatabase
import com.ewide.test.faris_ghilmany.core.data.source.remote.network.GameApiService
import kotlinx.coroutines.flow.Flow

class PagingDataSource(
    private val database: GameDatabase,
    private val apiServices: GameApiService,
) {

    fun getStories(): Flow<PagingData<GameEntity>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator =
            GameRemoteMediator(apiServices, database)
            ,
            pagingSourceFactory = {
                database.gameDao().getPagingGame()
            }
        ).flow
    }

}