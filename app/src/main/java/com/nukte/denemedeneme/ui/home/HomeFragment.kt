package com.nukte.denemedeneme.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ivbaranov.mfb.MaterialFavoriteButton
import com.google.android.material.snackbar.Snackbar
import com.nukte.denemedeneme.*
import com.nukte.denemedeneme.databinding.FragmentHomeBinding
import com.nukte.denemedeneme.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var button : MaterialFavoriteButton
    private val dashboardViewModel : DashboardViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var action : NavDirections

        val newsAdapter = ItemListAdapter()
        binding.recyclerView.adapter = newsAdapter

        homeViewModel.news.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it)
        }

        newsAdapter.onItemClicked = {
            action = HomeFragmentDirections.actionNavigationHomeToDetailScreen().setNews(it)
            findNavController().navigate(action)
        }
        newsAdapter.onSaveButtonClicked = {
            dashboardViewModel.saveNews(it)
            Snackbar.make(view,"Başarı ile kaydedildi.",Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}