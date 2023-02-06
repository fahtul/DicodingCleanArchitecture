package id.fahtul.core.data.source.remote.response.game

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListGameResponse(

    @field:SerializedName("next")
    val next: String,

    @field:SerializedName("previous")
    val previous: String,

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("results")
    val results: List<GameResponse>,

    ) : Parcelable