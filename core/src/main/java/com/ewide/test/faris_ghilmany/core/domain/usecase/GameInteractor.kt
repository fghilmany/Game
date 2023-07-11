package com.ewide.test.faris_ghilmany.core.domain.usecase

import androidx.paging.PagingData
import com.ewide.test.faris_ghilmany.core.data.GameRepository
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: GameRepository): GameUseCase {
    override fun getPagingGame(desc: String): Flow<PagingData<GameEntity>> {
        return gameRepository.getGame(desc)
    }
}