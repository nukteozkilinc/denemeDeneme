package com.nukte.denemedeneme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.ivbaranov.mfb.MaterialFavoriteButton
import com.nukte.denemedeneme.databinding.RecyclerLayoutBinding
import com.nukte.denemedeneme.databinding.SaveRecyclerLayoutBinding
import com.nukte.denemedeneme.ui.dashboard.DashboardViewModel

class ItemListAdapter() : ListAdapter<News, ItemListAdapter.ItemListViewHolder>(NewsComparator) {

    var onItemClicked: ((news: News) -> Unit)? = null
    var onSaveButtonClicked: ((news: News) -> Unit)? = null
    var onUnsaveButtonClicked: ((news: News) -> Unit)? = null

    class ItemListViewHolder(val binding: RecyclerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(news: News) {
            binding.saveButton.setFavorite(news.isSaved, false)
            Glide.with(itemView).load(news.urlToImage).into(binding.imageView)
            binding.descriptionText.text = news.description
            binding.titleText.text = news.title
            binding.publishedAt.text = news.publishedAt
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val binding =
            RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, parent, false)
        return ItemListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        holder.bindItems(getItem(position))
        holder.itemView.setOnClickListener {
            onItemClicked?.invoke(getItem(position))
        }

        holder.binding.saveButton.setOnFavoriteChangeListener { _, favorite ->
            when (favorite) {
                true -> onSaveButtonClicked?.invoke(getItem(position))
                false -> onUnsaveButtonClicked?.invoke(getItem(position))
            }
        }
    }
}

object NewsComparator : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem.description == newItem.description
}

