package id.fahtul.core.domain.usecase.detailgame

import id.fahtul.core.data.Resource
import id.fahtul.core.domain.model.DetailGame
import kotlinx.coroutines.flow.Flow

interface DetailGameUseCase {
    fun getDetailGame(id: Int): Flow<Resource<DetailGame?>>
}