package id.fahtul.dicodingexpertsubmission1.di

import id.fahtul.core.domain.usecase.detailgame.DetailGameInteractor
import id.fahtul.core.domain.usecase.detailgame.DetailGameUseCase
import id.fahtul.core.domain.usecase.game.GameInteractor
import id.fahtul.core.domain.usecase.game.GameUseCase
import id.fahtul.dicodingexpertsubmission1.detail.DetailGameViewModel
import id.fahtul.dicodingexpertsubmission1.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
    factory<DetailGameUseCase> { DetailGameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailGameViewModel(get()) }
}