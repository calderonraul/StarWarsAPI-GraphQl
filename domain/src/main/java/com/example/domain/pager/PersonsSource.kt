package com.example.domain.pager

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.repository.PersonsRepository

class PersonsSource(private val repository: PersonsRepository) : PagingSource<String, PersonDomain>() {
    override fun getRefreshKey(state: PagingState<String, PersonDomain>): String? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PersonDomain> {

        return try {
            val response = repository.getAllPersonsAPI(5,params.key ?: "")
            val prevKey=if(response?.pageInfoDomain?.hasPreviousPage==false)null else response?.pageInfoDomain?.endCursor
            val nextKey=if(response?.pageInfoDomain?.hasNextPage==false)null else response?.pageInfoDomain?.endCursor
            LoadResult.Page(
                data = response?.people?:emptyList(),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}