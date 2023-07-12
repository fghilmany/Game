package com.ewide.test.faris_ghilmany.core.data

import androidx.paging.PagingData
import androidx.paging.map
import com.ewide.test.faris_ghilmany.core.data.source.local.LocalDataSource
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.data.source.paging.PagingDataSource
import com.ewide.test.faris_ghilmany.core.data.source.remote.RemoteDataSource
import com.ewide.test.faris_ghilmany.core.domain.model.Game
import com.ewide.test.faris_ghilmany.core.domain.repository.IGameRepository
import com.ewide.test.faris_ghilmany.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val pagingDataSource: PagingDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {
    override fun getGame(searchQuery: String?, desc: String?): Flow<PagingData<Game>> {
        return pagingDataSource.getStories(searchQuery, desc).map {pagingData ->
            pagingData.map { game ->
                with(game){
                    Game(gameId, title, normalPrice, thumb, dealRating, favorite)
                }
            }
        }
    }
}