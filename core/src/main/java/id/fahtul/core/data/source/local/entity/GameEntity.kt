package id.fahtul.core.data.source.local.entity

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @SuppressLint("KotlinNullnessAnnotation") @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var gameId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "rating")
    val rating: Double?,

    @ColumnInfo(name = "saturated_color")
    val saturatedColor: String,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "released")
    val released: String,

    @ColumnInfo(name = "background_image")
    val backgroundImage: String,

    @ColumnInfo(name = "dominant_color")
    val dominantColor: String,

    @ColumnInfo(name = "updated")
    val updated: String,

    @ColumnInfo(name = "reviews_count")
    val reviewsCount: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)