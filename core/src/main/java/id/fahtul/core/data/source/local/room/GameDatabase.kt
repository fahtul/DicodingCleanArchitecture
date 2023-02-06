package id.fahtul.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.fahtul.core.data.source.local.entity.DetailGameEntity
import id.fahtul.core.data.source.local.entity.GameEntity

@Database(entities = [GameEntity::class, DetailGameEntity::class], version = 2, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {

    abstract fun gameDao(): GameDao
    abstract fun detailGameDao(): DetailGameDao
}