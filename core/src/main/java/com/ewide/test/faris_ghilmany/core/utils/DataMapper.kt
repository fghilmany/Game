package com.ewide.test.faris_ghilmany.core.utils

import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.data.source.remote.response.ListGameResponse

object DataMapper {
    fun mappingListGameResponseToGameEntity(list: List<ListGameResponse>?): List<GameEntity>? = list?.map {
        with(it){
            GameEntity(gameID.toString(), title, normalPrice, thumb, dealRating,)
        }
    }
}