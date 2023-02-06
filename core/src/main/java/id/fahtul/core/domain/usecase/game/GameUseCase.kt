package id.fahtul.core.domain.usecase.game

import id.fahtul.core.data.Resource
import id.fahtul.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGame(): Flow<Resource<List<Game>>>

    fun getFavoriteTourism(): Flow<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)
}