package com.example.domain.useCase

import androidx.paging.PagingData
import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.repository.PersonsRepository
import kotlinx.coroutines.flow.Flow


class GetPeopleUseCase(private val repository: PersonsRepository) {
    operator fun invoke(): Flow<PagingData<PersonDomain>>{
        return repository.getOrdersPaginated()
    }


}