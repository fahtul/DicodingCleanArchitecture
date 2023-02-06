package id.fahtul.core.data

import id.fahtul.core.data.source.local.DetailGameLocalDataSource
import id.fahtul.core.data.source.remote.RemoteDataSource
import id.fahtul.core.data.source.remote.network.ApiResponse
import id.fahtul.core.data.source.remote.response.detailgame.DetailGameResponse
import id.fahtul.core.domain.model.DetailGame
import id.fahtul.core.domain.repository.IDetailGameRepository
import id.fahtul.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailGameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val detailGameLocalDataSource: DetailGameLocalDataSource
): IDetailGameRepository {
    override fun getAllGame(id: Int): Flow<Resource<DetailGame?>> =
        object : NetworkBoundResource<DetailGame?, DetailGameResponse>() {
            override fun loadFromDB(): Flow<DetailGame?> {
                return detailGameLocalDataSource.getDetailGame(id).map {
                    DataMapper.mapDetailEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailGameResponse>> =
                remoteDataSource.getDetailGame(id)

            override suspend fun saveCallResult(data: DetailGameResponse) {
                val detailGame = DataMapper.mapDetailGameResponseToEntities(data)
                detailGameLocalDataSource.insertDetailGame(detailGame)
            }

            override fun shouldFetch(data: DetailGame?): Boolean =
                data == null

        }.asFlow()
}