package id.fahtul.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.fahtul.core.domain.model.Game
import id.fahtul.core.domain.usecase.game.GameUseCase

class FavoriteViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    val favoriteGame = gameUseCase.getFavoriteTourism().asLiveData()

    fun setFavoriteTourism(game: Game, newStatus: Boolean) =
        gameUseCase.setFavoriteGame(game, newStatus)
}