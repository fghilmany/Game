package com.ewide.test.faris_ghilmany.core.domain.model
import androidx.room.Entity
import androidx.room.PrimaryKey

data class DetailGame(
    val gameID: String = "",
    val metacriticScore: String? = null,
    val salePrice: String? = null,
    val releaseDate: Int? = null,
    val thumb: String? = null,
    val steamRatingCount: String? = null,
    val steamworks: String? = null,
    val metacriticLink: String? = null,
    val storeID: String? = null,
    val steamAppID: String? = null,
    val steamRatingPercent: String? = null,
    val name: String? = null,
    val publisher: String? = null,
    val retailPrice: String? = null,
    val steamRatingText: String? = null,
    var favorite: Boolean = false
)