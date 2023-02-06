package id.fahtul.dicodingexpertsubmission1.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.fahtul.core.domain.model.Game
import id.fahtul.core.domain.usecase.game.GameUseCase

class HomeViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    val game = gameUseCase.getAllGame().asLiveData()

    fun setFavoriteTourism(game: Game, newStatus: Boolean) =
        gameUseCase.setFavoriteGame(game, newStatus)
}