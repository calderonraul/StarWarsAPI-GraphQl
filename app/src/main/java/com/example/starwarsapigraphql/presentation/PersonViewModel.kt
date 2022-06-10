package com.example.starwarsapigraphql.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.entity.personDetail.PersonXDomain
import com.example.domain.pager.PersonsSource
import com.example.domain.useCase.GetPeopleDetailUseCase
import com.example.domain.useCase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val personsSource: PersonsSource,
    private val getPeopleDetailUseCase: GetPeopleDetailUseCase
) : ViewModel() {
    private val personsData: MutableStateFlow<List<PersonDomain>> = MutableStateFlow(emptyList())
    private val wordValueFlow: MutableStateFlow<String> = MutableStateFlow("")
    private val personDetailData: MutableStateFlow<PersonXDomain> = MutableStateFlow(
        PersonXDomain(
            id = "",
            name = "",
            hairColor = "",
            skinColor = "",
            eyeColor = "",
            birthYear = "",
            vehicleConnection = null
        )
    )

    val pagedInfo: Flow<PagingData<PersonDomain>> = Pager(PagingConfig(pageSize = 5)){
        personsSource

    }.flow.cachedIn(viewModelScope)



    private fun fetchList() {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }

    private fun fetchPersonDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            getPeopleDetailUseCase.initDb(wordValueFlow.value)
            getPeopleDetailUseCase.invoke().collect {
                personDetailData.value = it
            }
        }
    }

    private fun onWordChanged(value: String) {

        wordValueFlow.value = value
    }

    val registerState = PersonUIState(
        personFlow = personsData,
        fetchMoreData = this::fetchList,
        wordValue = wordValueFlow,
        onWordValueChanged = this::onWordChanged,
        fetchPersonDetail = this::fetchPersonDetail,
        personDetailFlow = personDetailData
    )
}