package com.ewide.test.faris_ghilmany.core.data

import androidx.paging.PagingData
import androidx.paging.map
import com.ewide.test.faris_ghilmany.core.data.source.local.LocalDataSource
import com.ewide.test.faris_ghilmany.core.data.source.paging.PagingDataSource
import com.ewide.test.faris_ghilmany.core.data.source.remote.RemoteDataSource
import com.ewide.test.faris_ghilmany.core.data.source.remote.network.ApiResponse
import com.ewide.test.faris_ghilmany.core.data.source.remote.response.DetailGameResponse
import com.ewide.test.faris_ghilmany.core.domain.model.DetailGame
import com.ewide.test.faris_ghilmany.core.domain.model.Game
import com.ewide.test.faris_ghilmany.core.domain.repository.IGameRepository
import com.ewide.test.faris_ghilmany.core.utils.AppExecutors
import com.ewide.test.faris_ghilmany.core.utils.DataMapper
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

    override fun getDetailGame(gameId: String): Flow<Resource<DetailGame>> {
        return object :NetworkBoundResource<DetailGame, DetailGameResponse>(){
            override fun loadFromDB(): Flow<DetailGame> {
                return localDataSource.getDetailGame(gameId).map {
                    DataMapper.mappingGameEntityToDetailGame(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailGameResponse>> {
                return remoteDataSource.getDetailGame(gameId)
            }

            override suspend fun saveCallResult(data: DetailGameResponse) {
                val detailGameEntity = DataMapper.mappingDetailGameResponseToDetailGameEntity(data)
                detailGameEntity?.let { localDataSource.insertDetailGame(it) }
            }

            override fun shouldFetch(data: DetailGame?): Boolean {
                return data == null
            }

        }.asFlow()
    }

    override fun getFavoriteGame(): Flow<List<DetailGame>> {
        return localDataSource.getFavoriteDetailGame().map { DataMapper.mappingListGameEntityToListDetailGame(it) }
    }

    override fun setGameFavorite(game: DetailGame, state: Boolean) {
        val detailGameEntity = DataMapper.mappingGameToGameEntity(game)
        appExecutors.diskIO().execute {
            localDataSource.setGameFavorite(detailGameEntity, state)
        }
    }
}