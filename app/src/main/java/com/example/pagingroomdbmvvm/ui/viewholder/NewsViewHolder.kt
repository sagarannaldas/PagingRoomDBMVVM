package com.example.pagingroomdbmvvm.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingroomdbmvvm.R
import com.example.pagingroomdbmvvm.data.model.New
import com.example.pagingroomdbmvvm.databinding.ItemListFooterBinding
import com.example.pagingroomdbmvvm.databinding.RecyclerItemBinding

class NewsViewHolder(private val binding: RecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val news: New? = null

    fun bind(news: New?) {
        if (news == null) {

        } else {
            showData(news)
        }

    }

    private fun showData(news: New) {
        binding.title.text = news.newsTitle
        binding.content.text = news.newsContent
        binding.viewsEye.text = news.totalViews.toString()
        binding.comment.text = news.totalComment.toString()

        Glide.with(itemView)
            .load(news.newsImage)
            .into(binding.newsImage)
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
            val binding = RecyclerItemBinding.bind(view)
            return NewsViewHolder(binding)
        }
    }

}