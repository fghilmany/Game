package com.ewide.test.faris_ghilmany.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.DetailGameEntity
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.RemoteKeys

@Database(entities = [GameEntity::class, RemoteKeys::class, DetailGameEntity::class] ,version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
}