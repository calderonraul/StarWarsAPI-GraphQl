package com.example.starwarsapigraphql.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.entity.personDetail.PersonXDomain
import com.example.domain.useCase.GetPeopleDetailUseCase
import com.example.domain.useCase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase,
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

    private fun fetchList() {
        viewModelScope.launch(Dispatchers.IO) {
            getPeopleUseCase.initDb()
            getPeopleUseCase.invoke().collect {
                personsData.value = it
            }
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