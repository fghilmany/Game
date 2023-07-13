package com.ewide.test.faris_ghilmany.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_game")
data class DetailGameEntity(

    @PrimaryKey
    @ColumnInfo("gameID")
    val gameID: String,

    @ColumnInfo("metacriticScore")
    val metacriticScore: String,

    @ColumnInfo("salePrice")
    val salePrice: String,

    @ColumnInfo("releaseDate")
    val releaseDate: Int,

    @ColumnInfo("thumb")
    val thumb: String,

    @ColumnInfo("steamRatingCount")
    val steamRatingCount: String,

    @ColumnInfo("steamworks")
    val steamworks: String,

    @ColumnInfo("metacriticLink")
    val metacriticLink: String,

    @ColumnInfo("storeID")
    val storeID: String,

    @ColumnInfo("steamAppID")
    val steamAppID: String,

    @ColumnInfo("steamRatingPercent")
    val steamRatingPercent: String,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("publisher")
    val publisher: String,

    @ColumnInfo("retailPrice")
    val retailPrice: String,

    @ColumnInfo("steamRatingText")
    val steamRatingText: String,

    @ColumnInfo("favorite")
    var favorite: Boolean = false
)