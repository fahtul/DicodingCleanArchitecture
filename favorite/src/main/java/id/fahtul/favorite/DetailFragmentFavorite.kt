package id.fahtul.favorite

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import id.fahtul.core.R
import id.fahtul.core.data.Resource
import id.fahtul.core.domain.model.DetailGame
import id.fahtul.dicodingexpertsubmission1.detail.DetailGameViewModel
import id.fahtul.favorite.databinding.FragmentDetailFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragmentFavorite : Fragment() {

    private val detailViewModel: DetailGameViewModel by viewModel()

    private var _binding: FragmentDetailFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {
            val detailGameId = DetailFragmentFavoriteArgs.fromBundle(arguments as Bundle).detailGame
            detailViewModel.getDetailGame(detailGameId).observe(viewLifecycleOwner) { detailGame ->
                if (detailGame != null) {
                    when (detailGame) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            showDetailGame(detailGame.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewErrorFavorite.tvError.visibility = View.VISIBLE
                            binding.viewErrorFavorite.tvError.text =
                                detailGame.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }
        }
    }

    private fun showDetailGame(detailGame: DetailGame?) {
        detailGame?.let {
            Glide.with(this)
                .load(detailGame.backgroundImage)
                .into(binding.ivItemPhoto)
            binding.tvItemName.text = detailGame.name
            binding.txtRating.text = detailGame.rating.toString()
            binding.txtReleaseDate.text = detailGame.released
            binding.txtReviewsCount.text = detailGame.reviewsCount.toString()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.tvItemDescription.text = Html.fromHtml(detailGame.description, Html.FROM_HTML_MODE_COMPACT)
            } else {
                binding.tvItemDescription.text = Html.fromHtml(detailGame.description)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}