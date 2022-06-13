package com.example.data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo3.api.Optional
import com.example.data.AllPeopleFromApiQuery
import com.example.data.api.PersonsAPI
import com.example.data.mappers.apolloMapper.ApolloClassAllPeopleMapper
import com.example.data.mappers.personsMapper.AllPeopleMapper
import com.example.domain.entity.allPeople.PersonDomain

class PersonsSource(private val api: PersonsAPI) : PagingSource<String, PersonDomain>() {
    override fun getRefreshKey(state: PagingState<String, PersonDomain>): String? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PersonDomain> {
        return try {
            val auxItem = Optional.Present(5)
            val auxCursor=Optional.Present(params.key?:"")

            val listAux =api.getApolloClient().query(AllPeopleFromApiQuery(auxItem, auxCursor)).execute().data
            val mapper=listAux?.allPeople?.let { ApolloClassAllPeopleMapper().mapToEntity(it) }

            val response = mapper?.let { AllPeopleMapper().mapFromEntity(it) }
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