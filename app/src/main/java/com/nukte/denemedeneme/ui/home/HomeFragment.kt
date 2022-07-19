package com.nukte.denemedeneme.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.load.engine.Resource
import com.nukte.denemedeneme.*
import com.nukte.denemedeneme.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
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

        binding.swipeRefresh.setOnRefreshListener{
            newsAdapter.refresh() //paging var diye fetchnews ile istek atmana gerek yok
        }
    }

    private fun observeViewModel() = with(homeViewModel) {
       /* news.observe(viewLifecycleOwner) {
            binding.recyclerView.post {
                lifecycleScope.launch {
                    newsAdapter.submitData(it)
                    binding.progressBar.isVisible = false
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        }*/
        lifecycleScope.launchWhenCreated {
            newsFlow.collectLatest{
                binding.progressBar.isVisible = false
                binding.swipeRefresh.isRefreshing = false
                newsAdapter.submitData(it)
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
        newsAdapter.refresh()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}