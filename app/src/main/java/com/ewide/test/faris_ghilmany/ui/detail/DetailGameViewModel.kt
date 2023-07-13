package com.ewide.test.faris_ghilmany.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ewide.test.faris_ghilmany.core.domain.model.DetailGame
import com.ewide.test.faris_ghilmany.core.domain.usecase.GameUseCase

class DetailGameViewModel(private val gameUseCase: GameUseCase): ViewModel() {

    private var favState: Boolean = false
    private var gameId: String = ""
    private lateinit var game: DetailGame

    fun setGameId(gameId: String?){
        if (gameId != null) {
            this.gameId = gameId
        }
    }
    fun setFavoriteGame(state: Boolean){
        this.favState = state
    }

    fun setDetailGame(game: DetailGame?){
        if (game != null) {
            this.game = game
        }
    }
    fun getDetailGame() = gameUseCase.getDetailGame(gameId).asLiveData()

    fun setFavoriteMovie() = gameUseCase.setGameFavorite(game, favState)
}