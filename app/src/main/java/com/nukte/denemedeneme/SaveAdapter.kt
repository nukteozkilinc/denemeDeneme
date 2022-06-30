package com.nukte.denemedeneme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nukte.denemedeneme.databinding.SaveRecyclerLayoutBinding

class SaveAdapter() : androidx.recyclerview.widget.ListAdapter<News, SaveAdapter.SaveViewHolder>(NewsComparator) {
    var onItemClicked: ((news: News) -> Unit)? = null
    var onUnsaveButtonClicked: ((news: News) -> Unit)? = null

    class SaveViewHolder (val binding : SaveRecyclerLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindSaveItems(news:News){
                Glide.with(itemView).load(news.urlToImage).into(binding.saveImageView)
                binding.saveDescriptionText.text = news.description
                binding.saveTitleText.text = news.title
                binding.savePublishedAt.text = news.publishedAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val binding = SaveRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return SaveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bindSaveItems(getItem(position))
        holder.itemView.setOnClickListener {
            onItemClicked?.invoke(getItem(position))
        }

        holder.binding.saveButton.isFavorite= true
        holder.binding.saveButton.setOnFavoriteAnimationEndListener{_,favorite ->
            when(favorite){
                false -> onUnsaveButtonClicked?.invoke(getItem(position))
            }
        }


    }
}
