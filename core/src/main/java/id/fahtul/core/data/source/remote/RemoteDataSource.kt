package id.fahtul.core.data.source.remote

import android.util.Log
import id.fahtul.core.data.source.remote.network.ApiResponse
import id.fahtul.core.data.source.remote.network.ApiService
import id.fahtul.core.data.source.remote.response.detailgame.DetailGameResponse
import id.fahtul.core.data.source.remote.response.game.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllGame(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.getGames()
                val dataArray = response.results
                if (dataArray.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(dataArray))
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.d("RemoteDataSourceGame", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailGame(gameId: Int): Flow<ApiResponse<DetailGameResponse>> {
        return flow {
            try {
                val response = apiService.getDetailGames(gameId)
                if (response.id == 0) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.d("RemoteDataSourceGame", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}