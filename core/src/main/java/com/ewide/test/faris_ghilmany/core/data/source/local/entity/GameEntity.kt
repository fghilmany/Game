package com.ewide.test.faris_ghilmany.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id_game")
    var gameId: Int = 0,

    @ColumnInfo("title")
    var title: String? = null,

    @ColumnInfo("normal_price")
    var normalPrice: String? = null,

    @ColumnInfo("thumb")
    var thumb: String? = null,

    @ColumnInfo("deal_rating")
    var dealRating: String? = null,

    @ColumnInfo("favorite")
    var favorite: Boolean? = false,


)