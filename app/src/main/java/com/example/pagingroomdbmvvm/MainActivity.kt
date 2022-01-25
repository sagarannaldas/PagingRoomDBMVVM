package com.example.pagingroomdbmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingroomdbmvvm.data.remote.NewsApi
import com.example.pagingroomdbmvvm.databinding.ActivityMainBinding
import com.example.pagingroomdbmvvm.ui.adapter.NewsAdapter
import com.example.pagingroomdbmvvm.ui.adapter.NewsLoadStateAdapter
import com.example.pagingroomdbmvvm.ui.model.MainRepository
import com.example.pagingroomdbmvvm.ui.model.MainViewModel
import com.example.pagingroomdbmvvm.ui.model.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = NewsApi()
        val repository = MainRepository(api)
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        initAdapter()
        loadData()

        binding.btRetry.setOnClickListener {
            newsAdapter.retry()
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.getNewsResult().collectLatest { pagingData ->
                newsAdapter.submitData(pagingData)
            }
        }

        lifecycleScope.launch {
            newsAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.recyclerView.scrollToPosition(0) }
        }
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = newsAdapter.withLoadStateHeaderAndFooter(
            header = NewsLoadStateAdapter { newsAdapter.retry() },
            footer = NewsLoadStateAdapter { newsAdapter.retry() }
        )

        newsAdapter.addLoadStateListener { loadState ->
            binding.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.btRetry.isVisible = loadState.source.refresh is LoadState.Error
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}