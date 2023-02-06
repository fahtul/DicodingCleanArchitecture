package id.fahtul.core.domain.repository

import id.fahtul.core.data.Resource
import id.fahtul.core.domain.model.DetailGame
import kotlinx.coroutines.flow.Flow

interface IDetailGameRepository {
    fun getAllGame(id: Int): Flow<Resource<DetailGame?>>
}