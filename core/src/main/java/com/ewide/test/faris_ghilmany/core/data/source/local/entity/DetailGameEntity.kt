package com.ewide.test.faris_ghilmany.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_game")
data class DetailGameEntity(

    @PrimaryKey
    @ColumnInfo("gameID")
    val gameID: String = "",

    @ColumnInfo("metacriticScore")
    val metacriticScore: String? = null,

    @ColumnInfo("salePrice")
    val salePrice: String? = null,

    @ColumnInfo("releaseDate")
    val releaseDate: Int? = null,

    @ColumnInfo("thumb")
    val thumb: String? = null,

    @ColumnInfo("steamRatingCount")
    val steamRatingCount: String? = null,

    @ColumnInfo("steamworks")
    val steamworks: String? = null,

    @ColumnInfo("metacriticLink")
    val metacriticLink: String? = null,

    @ColumnInfo("storeID")
    val storeID: String? = null,

    @ColumnInfo("steamAppID")
    val steamAppID: String? = null,

    @ColumnInfo("steamRatingPercent")
    val steamRatingPercent: String? = null,

    @ColumnInfo("name")
    val name: String? = null,

    @ColumnInfo("publisher")
    val publisher: String? = null,

    @ColumnInfo("retailPrice")
    val retailPrice: String? = null,

    @ColumnInfo("steamRatingText")
    val steamRatingText: String? = null,

    @ColumnInfo("favorite")
    var favorite: Boolean = false
)