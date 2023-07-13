package com.ewide.test.faris_ghilmany.core.domain.usecase

import androidx.paging.PagingData
import com.ewide.test.faris_ghilmany.core.data.Resource
import com.ewide.test.faris_ghilmany.core.domain.model.DetailGame
import com.ewide.test.faris_ghilmany.core.domain.model.Game
import com.ewide.test.faris_ghilmany.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getPagingGame(searchQuery: String?, desc: String?): Flow<PagingData<Game>> {
        return gameRepository.getGame(searchQuery, desc)
    }
    override fun getDetailGame(gameId: String): Flow<Resource<DetailGame>> {
        return gameRepository.getDetailGame(gameId)
    }
    override fun getFavoriteGame(): Flow<List<DetailGame>> {
        return gameRepository.getFavoriteGame()
    }
    override fun setGameFavorite(game: DetailGame, state: Boolean) {
        return gameRepository.setGameFavorite(game, state)
    }
}