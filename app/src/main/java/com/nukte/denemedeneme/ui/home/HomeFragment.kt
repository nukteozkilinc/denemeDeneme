package com.nukte.denemedeneme.ui.home
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.contains
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.nukte.denemedeneme.*
import com.nukte.denemedeneme.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
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
        var action : NavDirections


        val newsAdapter = ItemListAdapter()
        binding.recyclerView.adapter = newsAdapter

        with(homeViewModel){
            news.observe(viewLifecycleOwner) {
                viewModelScope.launch {
                    newsAdapter.submitData(it)
                }
            }
        }

        newsAdapter.onItemClicked = {
            action = HomeFragmentDirections.actionNavigationHomeToDetailScreen(it)
            findNavController().navigate(action)
        }
        newsAdapter.onSaveButtonClicked = {
            homeViewModel.saveNews(it)
        }

        newsAdapter.onUnsaveButtonClicked={
            homeViewModel.deleteNews(it)
        }



        binding.floatingActionButton2.setOnClickListener {
            action = HomeFragmentDirections.actionNavigationHomeToSearchFragment()
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.fetchNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}