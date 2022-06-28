package com.nukte.denemedeneme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SaveAdapter() : androidx.recyclerview.widget.ListAdapter<News, SaveAdapter.SaveViewHolder>(NewsComparator) {
    class SaveViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindSaveItems(news:News){

                val saveTitle = itemView.findViewById(R.id.saveTitleText) as TextView
                val saveDescription = itemView.findViewById(R.id.saveDescriptionText) as TextView
                val savePublishedAt = itemView.findViewById(R.id.savePublishedAt) as TextView
                val save_image_view = itemView.findViewById(R.id.save_image_view) as ImageView

                Glide.with(itemView).load(news.urlToImage).into(save_image_view)
                saveDescription.text = news.description
                saveTitle.text = news.title
                savePublishedAt.text = news.publishedAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.save_recycler_layout,parent,false)
        return SaveViewHolder(view)
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bindSaveItems(getItem(position))
    }
}
