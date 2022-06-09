package com.example.domain.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.repository.PersonsRepository

class PersonsSource(private val repository: PersonsRepository) : PagingSource<Int, PersonDomain>() {
    override fun getRefreshKey(state: PagingState<Int, PersonDomain>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PersonDomain> {
        val nextPage = params.key ?: 1
        return try {
            LoadResult.Page(
                data = repository.getAllPersonsAPI(nextPage),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}