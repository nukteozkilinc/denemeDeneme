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
import com.nukte.denemedeneme.ui.dashboard.DashboardViewModel

class ItemListAdapter() : ListAdapter<News, ItemListAdapter.ItemListViewHolder>(NewsComparator){

    var onItemClicked : ((news : News) -> Unit)?= null

    var saveNews : ((news:News) -> Unit) ?=null

    class ItemListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val saveBtn = itemView.findViewById(R.id.save_button) as Button

        fun bindItems(news : News){

            val title = itemView.findViewById(R.id.titleText) as TextView
            val description = itemView.findViewById(R.id.descriptionText) as TextView
            val publishedAt = itemView.findViewById(R.id.publishedAt) as TextView
            val image_view = itemView.findViewById(R.id.image_view) as ImageView

            Glide.with(itemView).load(news.urlToImage).into(image_view)
            description.text = news.description
            title.text = news.title
            publishedAt.text = news.publishedAt

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout,parent,false)
        return ItemListViewHolder(view)
    }



    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
       holder.bindItems(getItem(position))


        holder.itemView.setOnClickListener {
            onItemClicked?.invoke(getItem(position))
        }

        holder.saveBtn.setOnClickListener {
            saveNews?.invoke(getItem(position))
        }

    }
}

object NewsComparator : DiffUtil.ItemCallback<News>(){
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem.description == newItem.description
}

