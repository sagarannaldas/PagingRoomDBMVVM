package com.example.pagingroomdbmvvm.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.pagingroomdbmvvm.ui.viewholder.FooterViewHolder

class NewsLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<FooterViewHolder>() {

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        return FooterViewHolder.create(parent, retry)
    }
}