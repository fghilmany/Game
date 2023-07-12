package com.ewide.test.faris_ghilmany.core.data.source.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.RemoteKeys
import com.ewide.test.faris_ghilmany.core.data.source.local.room.GameDatabase
import com.ewide.test.faris_ghilmany.core.data.source.remote.network.GameApiService
import com.ewide.test.faris_ghilmany.core.utils.DataMapper

@OptIn(ExperimentalPagingApi::class)
class GameRemoteMediator(
    private val apiServices: GameApiService,
    private val database: GameDatabase
): RemoteMediator<Int, GameEntity>() {
    private var desc: String = "0"
    fun setSortDesc(desc: String){
        this.desc = desc
    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH ->{
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val responseData = apiServices.getListDeals(page, state.config.pageSize, desc)

            val endOfPaginationReached = responseData.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.gameDao().deleteRemoteKeys()
                    database.gameDao().deleteGame()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = responseData.map {
                    RemoteKeys(id = it.gameID.toString(), prevKey = prevKey, nextKey = nextKey)
                }
                keys.let { database.gameDao().insertAll(keys) }
                val storyMap = DataMapper.mappingListGameResponseToGameEntity(responseData)
                database.gameDao().insertGame(storyMap)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, GameEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            database.gameDao().getRemoteKeysId(data.gameId.toString())
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, GameEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            database.gameDao().getRemoteKeysId(data.gameId.toString())
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, GameEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.gameId?.let { id ->
                database.gameDao().getRemoteKeysId(id.toString())
            }
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    companion object{
        private const val INITIAL_PAGE_INDEX = 1
    }

}