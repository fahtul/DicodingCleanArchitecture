package id.fahtul.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.fahtul.core.ui.GameAdapter
import id.fahtul.favorite.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            loadKoinModules(favoriteModule)

            val gameAdapter = GameAdapter()
            gameAdapter.onItemClick = { selectedData ->
                val toDetailGame = FavoriteFragmentDirections.actionFavoriteToDetailFragment()
                toDetailGame.detailGame = selectedData.id
                findNavController().navigate(toDetailGame)
            }
            gameAdapter.onExpandClick = { position ->
                gameAdapter.updateDataExpand(position)
            }
            gameAdapter.onFavoriteClick = { game ->
                favoriteViewModel.setFavoriteTourism(game, !game.isFavorite)
            }

            favoriteViewModel.favoriteGame.observe(viewLifecycleOwner) { dataGame ->
                gameAdapter.setData(dataGame)
                binding.viewEmpty.root.visibility =
                    if (dataGame.isNotEmpty()) View.GONE else View.VISIBLE
            }

            binding.rvGame.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }
}