package com.example.pagingroomdbmvvm.ui.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pagingroomdbmvvm.data.model.New
import com.example.pagingroomdbmvvm.data.remote.NewsApi
import kotlinx.coroutines.flow.Flow

class MainRepository(private val api: NewsApi) {

    fun getNewsResult(): Flow<PagingData<New>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { PagingDataSource(api) }
        ).flow
    }
}