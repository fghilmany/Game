package com.ewide.test.faris_ghilmany.core.utils

import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.data.source.remote.response.ListDealsResponseItem

object DataMapper {
    fun mappingListGameResponseToGameEntity(list: List<ListDealsResponseItem>?): List<GameEntity>? = list?.map {
        with(it){
            GameEntity((gameID ?: "0").toInt(), title, normalPrice, thumb, dealRating,)
        }
    }
}