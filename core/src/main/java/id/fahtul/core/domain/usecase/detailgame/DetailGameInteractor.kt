package id.fahtul.core.domain.usecase.detailgame

import id.fahtul.core.data.Resource
import id.fahtul.core.domain.model.DetailGame
import id.fahtul.core.domain.repository.IDetailGameRepository
import kotlinx.coroutines.flow.Flow

class DetailGameInteractor(private val detailGameRepository: IDetailGameRepository):
    DetailGameUseCase {
    override fun getDetailGame(id: Int): Flow<Resource<DetailGame?>> = detailGameRepository.getAllGame(id)
}