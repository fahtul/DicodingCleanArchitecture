package id.fahtul.core.data.source.local

import id.fahtul.core.data.source.local.entity.GameEntity
import id.fahtul.core.data.source.local.room.GameDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GameLocalDataSource(private val gameDao: GameDao) {

    fun getAllGame(): Flow<List<GameEntity>> = gameDao.getAllGame()

    fun getFavoriteGame(): Flow<List<GameEntity>> = gameDao.getFavoriteGame()

    fun insertGame(gameList: List<GameEntity>) =
        CoroutineScope(Dispatchers.IO).launch { gameDao.insertGame(gameList) }

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        CoroutineScope(Dispatchers.IO).launch { gameDao.updateFavoriteGame(game) }
    }
}