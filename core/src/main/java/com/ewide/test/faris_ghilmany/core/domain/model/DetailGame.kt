package com.ewide.test.faris_ghilmany.core.domain.model
import androidx.room.Entity
import androidx.room.PrimaryKey

data class DetailGame(
    val gameID: String,
    val metacriticScore: String,
    val salePrice: String,
    val releaseDate: Int,
    val thumb: String,
    val steamRatingCount: String,
    val steamworks: String,
    val metacriticLink: String,
    val storeID: String,
    val steamAppID: String,
    val steamRatingPercent: String,
    val name: String,
    val publisher: String,
    val retailPrice: String,
    val steamRatingText: String,
    var favorite: Boolean = false
)