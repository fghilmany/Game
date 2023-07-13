package com.ewide.test.faris_ghilmany.di

import com.ewide.test.faris_ghilmany.core.domain.usecase.GameInteractor
import com.ewide.test.faris_ghilmany.core.domain.usecase.GameUseCase
import com.ewide.test.faris_ghilmany.ui.detail.DetailGameViewModel
import com.ewide.test.faris_ghilmany.ui.favorite.FavoriteViewModel
import com.ewide.test.faris_ghilmany.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailGameViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}