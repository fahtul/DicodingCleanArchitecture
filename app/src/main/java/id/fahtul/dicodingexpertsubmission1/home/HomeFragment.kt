package id.fahtul.dicodingexpertsubmission1.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.fahtul.core.data.Resource
import id.fahtul.core.ui.GameAdapter
import id.fahtul.dicodingexpertsubmission1.R
import id.fahtul.dicodingexpertsubmission1.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {
            val gameAdapter = GameAdapter()
            gameAdapter.onItemClick = { selectedData ->
                val toDetailGame = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                toDetailGame.detailGame = selectedData.id
                findNavController().navigate(toDetailGame)
            }
            gameAdapter.onExpandClick = { position ->
                gameAdapter.updateDataExpand(position)
            }
            gameAdapter.onFavoriteClick = { game ->
                homeViewModel.setFavoriteTourism(game, !game.isFavorite)
            }

            homeViewModel.game.observe(viewLifecycleOwner) { game ->
                if (game != null) {
                    when (game) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            gameAdapter.setData(game.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.tvError.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                game.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            binding.rvGame.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }
}