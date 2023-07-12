package com.ewide.test.faris_ghilmany.core.domain.repository

import androidx.paging.PagingData
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getGame(searchQuery: String? = null, desc: String? = "0"): Flow<PagingData<Game>>
}