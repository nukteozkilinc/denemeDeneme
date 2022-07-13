package com.nukte.denemedeneme.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nukte.denemedeneme.R
import com.nukte.denemedeneme.SearchAdapter
import com.nukte.denemedeneme.databinding.FragmentSearchBinding
import com.nukte.denemedeneme.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchAdapter = SearchAdapter()
        binding.searchRecyclerView.adapter = searchAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchViewModel.getSearchNews(p0.toString())
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        searchAdapter.onItemClicked = {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailScreen(it)
            findNavController().navigate(action)
        }

        searchAdapter.onSaveButtonClicked = {
            searchViewModel.saveNews(it)
        }

        searchAdapter.onUnsaveButtonClicked = {
            searchViewModel.deleteNews(it)
        }

        searchViewModel.news.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        searchViewModel.refreshSearchNews()
    }
}