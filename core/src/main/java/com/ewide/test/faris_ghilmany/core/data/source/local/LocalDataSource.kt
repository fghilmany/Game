package com.ewide.test.faris_ghilmany.core.data.source.local

import com.ewide.test.faris_ghilmany.core.data.source.local.entity.DetailGameEntity
import com.ewide.test.faris_ghilmany.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {

    fun getDetailGame(gameId: String): Flow<DetailGameEntity> = gameDao.getDetailGame(gameId)
    suspend fun insertDetailGame(game: DetailGameEntity) = gameDao.insertDetailGame(game)

    fun getFavoriteDetailGame(): Flow<List<DetailGameEntity>> = gameDao.getFavoriteGame()
    fun setGameFavorite(game: DetailGameEntity, newState: Boolean) {
        game.favorite = newState
        gameDao.updateFavoriteGame(game)
    }
}