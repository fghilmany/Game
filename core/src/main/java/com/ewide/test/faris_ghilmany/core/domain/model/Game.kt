package com.ewide.test.faris_ghilmany.core.domain.model

data class GameEntity(
    var gameId: Int = 0,
    var title: String? = null,
    var normalPrice: String? = null,
    var thumb: String? = null,
    var dealRating: String? = null,
    var favorite: Boolean? = false,
)