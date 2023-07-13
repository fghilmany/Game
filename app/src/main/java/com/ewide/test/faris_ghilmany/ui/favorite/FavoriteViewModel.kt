package com.ewide.test.faris_ghilmany.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ewide.test.faris_ghilmany.core.domain.model.DetailGame
import com.ewide.test.faris_ghilmany.core.domain.model.Game
import com.ewide.test.faris_ghilmany.core.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(private val gameUseCase: GameUseCase): ViewModel() {

    private var _getFavoriteGame : MutableLiveData<List<DetailGame>> = MutableLiveData()
    val getFavoriteGame get() = _getFavoriteGame

    fun getFavoriteGame(){
        viewModelScope.launch {
            gameUseCase.getFavoriteGame().collect{
                _getFavoriteGame.value = it
            }
        }
    }
}