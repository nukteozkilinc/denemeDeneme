package com.nukte.denemedeneme.ui.detail
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nukte.denemedeneme.databinding.DetailScreenFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.net.URI

@AndroidEntryPoint
class DetailScreen : Fragment() {

    private val args : DetailScreenArgs by navArgs()
    private val detailScreenViewModel : DetailScreenViewModel by viewModels()
    private var _binding: DetailScreenFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailScreenFragmentBinding.inflate(
            inflater, container, false
        )
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.news?.let {
            Glide.with(view).load(it.media).into(binding.imageDetail)
            binding.titleDetail.text = it.title
            binding.contentDetail.text = it.summary
            binding.dateDetail.text = it.published_date
            binding.saveButton.isFavorite = it.isSaved
        }


        binding.saveButton.setOnFavoriteChangeListener{_,favorite ->
            args.news.isSaved = true
            when(favorite){
                true -> detailScreenViewModel.saveNews(args.news)
            }

        }
        binding.saveButton.setOnFavoriteAnimationEndListener{_,favorite->
            args.news.isSaved = false
            when(favorite){
                false -> detailScreenViewModel.deleteNews(args.news)
            }
        }

        binding.shareButton.setOnClickListener{
            val uri = URI(args.news.link)
            println("URL $uri")
            shareNews(uri)

        }
    }

    private fun shareNews(uri : URI){
        val intent = Intent( )
        intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION)
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT,"Habere Göz At! ${uri.toURL()}")
        intent.type = "text/plugin"
        startActivity(Intent.createChooser(intent, "Share to : "))


    }
}
