package com.example.domain.useCase

import com.example.domain.entity.PersonDomain
import com.example.domain.repository.PersonsRepository
import kotlinx.coroutines.flow.Flow


class GetPeopleUseCase(private val repository: PersonsRepository) {
    operator fun invoke(): Flow<List<PersonDomain>>{
        return repository.getPersonsFromRoom()
    }

    suspend fun initDb(){
        repository.getAllPersonsAPI()
    }
}