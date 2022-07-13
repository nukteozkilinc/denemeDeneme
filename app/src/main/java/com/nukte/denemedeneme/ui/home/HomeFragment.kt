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
    private val newsAdapter by lazy { ItemListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeRecyclerView()
        initFloatActionButtonActions()
        observeViewModel()
    }

    private fun observeViewModel() = with(homeViewModel) {
        news.observe(viewLifecycleOwner) {
            binding.recyclerView.post {
                lifecycleScope.launch {
                    newsAdapter.submitData(it)
                }
            }
        }
    }

    private fun initFloatActionButtonActions() = with(binding.floatingActionButton2) {
        setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToSearchFragment()
            findNavController().navigate(action)
        }
    }

    private fun initHomeRecyclerView() {
        with(newsAdapter) {
            binding.recyclerView.adapter = this

            onItemClicked = {
                val action = HomeFragmentDirections.actionNavigationHomeToDetailScreen(it)
                findNavController().navigate(action)
            }

            onSaveButtonClicked = {
                homeViewModel.saveNews(it)
            }

            onUnsaveButtonClicked = {
                homeViewModel.deleteNews(it)
            }
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