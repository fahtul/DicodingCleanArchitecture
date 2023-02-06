package id.fahtul.core.data.source.remote.network

import id.fahtul.core.data.source.remote.response.detailgame.DetailGameResponse
import id.fahtul.core.data.source.remote.response.game.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("games")
    suspend fun getGames(): ListGameResponse

    @GET("games/{gameId}")
    suspend fun getDetailGames(@Path(value = "gameId") gameId: Int): DetailGameResponse

}