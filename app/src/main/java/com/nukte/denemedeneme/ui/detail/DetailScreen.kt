package com.nukte.denemedeneme.ui.detail

import android.app.AlertDialog
import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nukte.denemedeneme.R
import com.nukte.denemedeneme.databinding.DetailScreenFragmentBinding
import com.nukte.denemedeneme.databinding.FragmentHomeBinding
import javax.security.auth.callback.Callback

class DetailScreen : Fragment() {
private val args : DetailScreenArgs by navArgs()
    private var _binding: DetailScreenFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = DetailScreen()
    }

    private lateinit var viewModel: DetailScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailScreenFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.news?.let {
            Glide.with(view).load(it.urlToImage).into(binding.imageDetail)
            binding.titleDetail.text = it.title
            binding.contentDetail.text = it.content
            binding.dateDetail.text = it.publishedAt
        }


    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}