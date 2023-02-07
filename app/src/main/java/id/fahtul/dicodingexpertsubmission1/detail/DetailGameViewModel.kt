package id.fahtul.dicodingexpertsubmission1.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.fahtul.core.domain.usecase.detailgame.DetailGameUseCase

class DetailGameViewModel(private val detailGameUseCase: DetailGameUseCase) : ViewModel() {

    fun getDetailGame(id: Int) = detailGameUseCase.getDetailGame(id).asLiveData()
}