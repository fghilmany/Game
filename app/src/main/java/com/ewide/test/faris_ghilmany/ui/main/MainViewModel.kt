package com.ewide.test.faris_ghilmany.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ewide.test.faris_ghilmany.core.domain.usecase.GameUseCase

class MainViewModel(gameUseCase: GameUseCase): ViewModel() {
    val getGame = gameUseCase.getPagingGame().asLiveData()
}