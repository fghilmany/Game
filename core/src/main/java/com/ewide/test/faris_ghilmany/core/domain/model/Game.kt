package com.ewide.test.faris_ghilmany.core.domain.model

data class Game(
    var gameId: String = "",
    var title: String? = null,
    var normalPrice: String? = null,
    var thumb: String? = null,
    var dealRating: String? = null,
    var favorite: Boolean? = false,
)