package com.ewide.test.faris_ghilmany.core.domain.repository

import androidx.paging.PagingData
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getGame(desc: String = "0"): Flow<PagingData<GameEntity>>
}