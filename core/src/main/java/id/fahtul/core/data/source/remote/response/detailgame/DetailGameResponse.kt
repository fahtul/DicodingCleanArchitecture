package id.fahtul.core.data.source.remote.response.detailgame

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailGameResponse(

    @field:SerializedName("rating")
    val rating: Double?,

    @field:SerializedName("saturated_color")
    val saturatedColor: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("slug")
    val slug: String,

    @field:SerializedName("released")
    val released: String,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("dominant_color")
    val dominantColor: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("updated")
    val updated: String,

    @field:SerializedName("reviews_count")
    val reviewsCount: Int,

    @field:SerializedName("description")
    val description: String
) : Parcelable