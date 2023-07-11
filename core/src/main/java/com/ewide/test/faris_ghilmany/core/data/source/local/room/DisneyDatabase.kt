package com.ewide.test.faris_ghilmany.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 0, exportSchema = false)
abstract class DisneyDatabase: RoomDatabase() {
    abstract fun disneyDao(): DisneyDao
}