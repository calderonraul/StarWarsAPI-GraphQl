package com.example.starwarsapigraphql.presentation

import androidx.paging.PagingData
import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.entity.personDetail.PersonXDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


data class PersonUIState(
    val personFlow: StateFlow<List<PersonDomain>>,
    val fetchMoreData: () -> Unit,
    val personDetailFlow: StateFlow<PersonXDomain>,
    val fetchPersonDetail: () -> Unit,
    val wordValue: StateFlow<String>,
    val onWordValueChanged: (String) -> Unit,
    val fetchPersonsPaginated: () -> Flow<PagingData<PersonDomain>>
)