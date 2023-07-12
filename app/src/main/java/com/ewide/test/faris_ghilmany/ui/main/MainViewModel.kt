package com.ewide.test.faris_ghilmany.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ewide.test.faris_ghilmany.core.domain.model.Game
import com.ewide.test.faris_ghilmany.core.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val gameUseCase: GameUseCase): ViewModel() {

    private var _getGame : MutableLiveData<PagingData<Game>> = MutableLiveData()
    val getGame get() = _getGame

    private var searchQuery: String? = null
    private var sort: String? = null

    fun setSearchQuery(searchQuery: String?){
        this.searchQuery = searchQuery
    }
    fun setSort(sort: String){
        this.sort = sort
    }
    fun getGame() {
        viewModelScope.launch{
            gameUseCase.getPagingGame(searchQuery, sort).collect {
                _getGame.value = it
            }
        }
    }
}