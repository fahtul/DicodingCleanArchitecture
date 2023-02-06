package id.fahtul.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val rating: Double?,
    val saturatedColor: String,
    val slug: String,
    val released: String,
    val backgroundImage: String,
    val dominantColor: String,
    val name: String,
    val updated: String,
    val reviewsCount: Int,
    val isFavorite: Boolean,
    var isExpand: Boolean = true
) : Parcelable