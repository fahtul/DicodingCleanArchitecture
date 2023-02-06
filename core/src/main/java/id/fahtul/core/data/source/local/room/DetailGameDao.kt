package id.fahtul.core.data.source.local.room

import androidx.room.*
import id.fahtul.core.data.source.local.entity.DetailGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailGameDao {
    @Query("SELECT * FROM detail_game where detail_id=:id")
    fun getDetailGame(id: Int): Flow<DetailGameEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: DetailGameEntity)

}