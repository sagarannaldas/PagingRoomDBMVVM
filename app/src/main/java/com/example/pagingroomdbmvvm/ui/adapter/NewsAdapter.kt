package com.example.pagingroomdbmvvm.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingroomdbmvvm.data.model.New
import com.example.pagingroomdbmvvm.ui.viewholder.NewsViewHolder

class NewsAdapter : PagingDataAdapter<New, RecyclerView.ViewHolder>(COMPARATOR) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = getItem(position)
        if (news != null) {
            (holder as NewsViewHolder).bind(news)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder.create(parent)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<New>() {
            override fun areItemsTheSame(oldItem: New, newItem: New): Boolean {
                return oldItem.newsId == newItem.newsId
            }

            override fun areContentsTheSame(oldItem: New, newItem: New): Boolean =
                oldItem == newItem
        }
    }
}