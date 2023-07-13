package com.ewide.test.faris_ghilmany.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ewide.test.faris_ghilmany.core.domain.usecase.GameUseCase

class FavoriteViewModel(private val gameUseCase: GameUseCase): ViewModel() {

    fun getFavoriteGame() = gameUseCase.getFavoriteGame().asLiveData()
}