package id.fahtul.core.util

import id.fahtul.core.data.source.local.entity.DetailGameEntity
import id.fahtul.core.data.source.local.entity.GameEntity
import id.fahtul.core.data.source.remote.response.detailgame.DetailGameResponse
import id.fahtul.core.data.source.remote.response.game.GameResponse
import id.fahtul.core.domain.model.DetailGame
import id.fahtul.core.domain.model.Game

object DataMapper {

    fun mapResponseToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                gameId = it.id,
                name = it.name,
                rating = it.rating,
                saturatedColor = it.saturatedColor,
                slug = it.slug,
                released = it.released,
                dominantColor = it.dominantColor,
                backgroundImage = it.backgroundImage,
                updated = it.updated,
                reviewsCount = it.reviewsCount,
                isFavorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.gameId,
                rating = it.rating,
                saturatedColor = it.saturatedColor,
                slug = it.slug,
                released = it.released,
                backgroundImage = it.backgroundImage,
                dominantColor = it.dominantColor,
                name = it.name,
                updated = it.updated,
                reviewsCount = it.reviewsCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        gameId = input.id,
        name = input.name,
        rating = input.rating,
        saturatedColor = input.saturatedColor,
        slug = input.slug,
        released = input.released,
        backgroundImage = input.backgroundImage,
        dominantColor = input.dominantColor,
        updated = input.updated,
        reviewsCount = input.reviewsCount,
        isFavorite = input.isFavorite
    )

    fun mapDetailGameResponseToEntities(input: DetailGameResponse): DetailGameEntity {
        val detailGame =
            input.let {
                DetailGameEntity(
                    gameId = it.id,
                    name = it.name,
                    rating = it.rating,
                    saturatedColor = it.saturatedColor,
                    slug = it.slug,
                    released = it.released,
                    dominantColor = it.dominantColor,
                    backgroundImage = it.backgroundImage,
                    updated = it.updated,
                    reviewsCount = it.reviewsCount,
                    description = it.description
                )
            }
        return detailGame
    }

    fun mapDetailEntitiesToDomain(input: DetailGameEntity?): DetailGame? =
        input?.let {
            DetailGame(
                id = it.gameId,
                rating = it.rating,
                saturatedColor = it.saturatedColor,
                slug = it.slug,
                released = it.released,
                backgroundImage = it.backgroundImage,
                dominantColor = it.dominantColor,
                name = it.name,
                updated = it.updated,
                reviewsCount = it.reviewsCount,
                description = it.description
            )
        }
}