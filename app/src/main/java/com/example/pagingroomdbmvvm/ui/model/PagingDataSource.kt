package com.example.pagingroomdbmvvm.ui.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingroomdbmvvm.data.model.New
import com.example.pagingroomdbmvvm.data.remote.NewsApi
import retrofit2.HttpException
import java.io.IOException

class PagingDataSource(private val api: NewsApi) : PagingSource<Int, New>() {

    override fun getRefreshKey(state: PagingState<Int, New>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, New> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.getNewsResult(nextPageNumber)
            val data = response.news
            LoadResult.Page(
                data = data,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (data.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}