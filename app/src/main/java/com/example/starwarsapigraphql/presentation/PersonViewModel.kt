package com.example.starwarsapigraphql.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PersonDomain
import com.example.domain.useCase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase
) : ViewModel() {
    private val personsData: MutableStateFlow<List<PersonDomain>> = MutableStateFlow(emptyList())


    private fun fetchList() {
        viewModelScope.launch(Dispatchers.IO) {
            getPeopleUseCase.initDb()
            getPeopleUseCase.invoke().collect{
                personsData.value=it
            }
        }
    }

    val registerState=PersonUIState(
        personFlow = personsData,
        fetchMoreData = this::fetchList
    )
}