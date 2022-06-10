package com.example.domain.pager

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.repository.PersonsRepository

class PersonsSource(private val repository: PersonsRepository) : PagingSource<String, PersonDomain>() {
    override fun getRefreshKey(state: PagingState<String, PersonDomain>): String? {
        return state.anchorPosition?.toString()
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PersonDomain> {

        return try {
            val response = repository.getAllPersonsAPI(50, params.key ?: "" )
            val nextPage = if (response)
            LoadResult.Page(

                data = repository.getAllPersonsAPI(nextPage,""),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}