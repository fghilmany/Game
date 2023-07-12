package com.ewide.test.faris_ghilmany.core.data.source.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.data.source.local.room.GameDatabase
import com.ewide.test.faris_ghilmany.core.data.source.remote.network.GameApiService
import com.ewide.test.faris_ghilmany.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

class PagingDataSource(
    private val database: GameDatabase,
    private val apiServices: GameApiService,
) {
    fun getStories(searchQuery: String? = null, desc: String? = "0"): Flow<PagingData<GameEntity>> {
        val gameRemoteMediator = GameRemoteMediator(apiServices, database)
        gameRemoteMediator.setSortDesc(desc)
        gameRemoteMediator.setSearchQuery(searchQuery)
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                initialLoadSize = 5
            ),
            remoteMediator = gameRemoteMediator
            ,
            pagingSourceFactory = {
                database.gameDao().getPagingGame()
            }
        ).flow
    }

}