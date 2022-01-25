package com.example.pagingroomdbmvvm.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagingroomdbmvvm.data.model.New
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val newsResult: Flow<PagingData<New>> = repository.getNewsResult().cachedIn(viewModelScope)


    fun getNewsResult():Flow<PagingData<New>> {
        return newsResult
    }
}