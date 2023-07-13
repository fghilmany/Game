package com.ewide.test.faris_ghilmany.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.DetailGameEntity
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.RemoteKeys
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao{
    @Query("SELECT * FROM game")
    fun getPagingGame(): PagingSource<Int, GameEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(storyEntity: List<GameEntity>?)
    @Query("DELETE FROM game")
    fun deleteGame()
    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun getRemoteKeysId(id: String): RemoteKeys?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)
    @Query("DELETE FROM remote_keys")
    suspend fun deleteRemoteKeys()
    @Query("SELECT * FROM detail_game where gameID = :gameId")
    fun getDetailGame(gameId: String): Flow<DetailGameEntity>
    @Query("SELECT * FROM detail_game where favorite = 1")
    fun getFavoriteGame(): Flow<List<DetailGameEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailGame(movie: DetailGameEntity)
    @Update
    fun updateFavoriteGame(game: DetailGameEntity)
}