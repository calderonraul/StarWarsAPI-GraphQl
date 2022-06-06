package com.example.starwarsapigraphql.presentation

import com.example.domain.entity.PersonDomain
import kotlinx.coroutines.flow.StateFlow


data class PersonUIState(
    val personFlow: StateFlow<List<PersonDomain>>,
    val fetchMoreData: () -> Unit
)