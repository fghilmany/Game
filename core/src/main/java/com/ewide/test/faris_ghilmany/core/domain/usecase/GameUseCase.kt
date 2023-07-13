package com.ewide.test.faris_ghilmany.core.domain.usecase

import androidx.paging.PagingData
import com.ewide.test.faris_ghilmany.core.data.Resource
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.domain.model.DetailGame
import com.ewide.test.faris_ghilmany.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase{
    fun getPagingGame(searchQuery: String? = null, desc: String? = "0"): Flow<PagingData<Game>>
    fun getDetailGame(gameId: String): Flow<Resource<DetailGame>>
    fun getFavoriteGame(): Flow<List<DetailGame>>
    fun setGameFavorite(game: DetailGame, state: Boolean)
}