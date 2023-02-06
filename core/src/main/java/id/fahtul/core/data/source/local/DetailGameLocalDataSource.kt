package id.fahtul.core.data.source.local

import id.fahtul.core.data.source.local.entity.DetailGameEntity
import id.fahtul.core.data.source.local.room.DetailGameDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailGameLocalDataSource(private val detailGameDao: DetailGameDao) {

    fun getDetailGame(id: Int): Flow<DetailGameEntity?> = detailGameDao.getDetailGame(id)

    fun insertDetailGame(detailGameEntity: DetailGameEntity) =
        CoroutineScope(Dispatchers.IO).launch { detailGameDao.insertGame(detailGameEntity) }
}