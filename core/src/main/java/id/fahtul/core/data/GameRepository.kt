package id.fahtul.core.data

import id.fahtul.core.data.source.local.GameLocalDataSource
import id.fahtul.core.data.source.remote.RemoteDataSource
import id.fahtul.core.data.source.remote.network.ApiResponse
import id.fahtul.core.data.source.remote.response.game.GameResponse
import id.fahtul.core.domain.model.Game
import id.fahtul.core.domain.repository.IGameRepository
import id.fahtul.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val gameLocalDataSource: GameLocalDataSource
): IGameRepository {
    override fun getAllGame(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return gameLocalDataSource.getAllGame().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getAllGame()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponseToEntities(data)
                gameLocalDataSource.insertGame(gameList)
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data == null || data.isEmpty()

        }.asFlow()

    override fun getFavoriteGame(): Flow<List<Game>> {
        return gameLocalDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        gameLocalDataSource.setFavoriteGame(gameEntity, state)
    }

}