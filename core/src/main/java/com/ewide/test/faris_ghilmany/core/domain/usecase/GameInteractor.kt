package com.ewide.test.faris_ghilmany.core.domain.usecase

import androidx.paging.PagingData
import com.ewide.test.faris_ghilmany.core.data.GameRepository
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.domain.model.Game
import com.ewide.test.faris_ghilmany.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getPagingGame(desc: String): Flow<PagingData<Game>> {
        return gameRepository.getGame(desc)
    }
}