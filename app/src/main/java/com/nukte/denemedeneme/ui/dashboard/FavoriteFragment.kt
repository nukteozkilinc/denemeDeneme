package com.nukte.denemedeneme.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.nukte.denemedeneme.SaveAdapter
import com.nukte.denemedeneme.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()
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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var action : NavDirections

        val saveAdapter = SaveAdapter()
        binding.saveRecyclerView.adapter = saveAdapter
        saveAdapter.onItemClicked = {
            action = FavoriteFragmentDirections.actionNavigationDashboardToDetailScreen().setNews(it)
            findNavController().navigate(action)
        }
        favoriteViewModel.getSavedNews().observe(viewLifecycleOwner) {
            saveAdapter.submitList(it)
        }

        saveAdapter.onUnsaveButtonClicked={
            favoriteViewModel.deleteNews(it)
        }

    }
}