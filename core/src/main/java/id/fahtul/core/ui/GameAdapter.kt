package id.fahtul.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.fahtul.core.R
import id.fahtul.core.databinding.ItemGameBinding
import id.fahtul.core.domain.model.Game

class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {

    private var listData = ArrayList<Game>()
    var onItemClick: ((Game) -> Unit)? = null
    var onExpandClick: ((Int) -> Unit)? = null
    var onFavoriteClick: ((Game) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Game>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
    fun updateDataExpand(position: Int) {
        listData[position].isExpand = !listData[position].isExpand
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGameBinding.bind(itemView)

        fun bind(data: Game) {
            with(binding) {
                val drawable = if (data.isExpand) {
                    R.drawable.baseline_keyboard_arrow_up
                } else {
                    R.drawable.baseline_keyboard_arrow_down_24
                }
                Glide.with(itemView.context)
                    .load(data.backgroundImage)
                    .into(imgGame)
                txtTitle.text = data.name
                txtReleaseDate.text = data.released
                txtRating.text = data.rating.toString()
                txtReviewsCount.text = data.reviewsCount.toString()
                groupDetail.visibility = if (data.isExpand) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
                imgExpand.setImageResource(drawable)
                binding.fab.setImageResource(getImageStatus(data.isFavorite))
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
            binding.imgExpand.setOnClickListener {
                onExpandClick?.invoke(adapterPosition)
            }
            binding.fab.setOnClickListener {
                onFavoriteClick?.invoke(listData[adapterPosition])
            }
        }
    }

    private fun getImageStatus(statusFavorite: Boolean): Int {
        return if (statusFavorite) {
            R.drawable.ic_favorite_white
        } else {
            R.drawable.ic_not_favorite_white
        }
    }
}