package com.example.domain.useCase

import com.example.domain.entity.personDetail.PersonXDomain
import com.example.domain.repository.PersonsRepository
import kotlinx.coroutines.flow.Flow

class GetPeopleDetailUseCase(private val peopleRepository: PersonsRepository) {
    operator fun invoke(): Flow<PersonXDomain> = peopleRepository.getPersonByIdFromRoom()
    suspend fun initDb(id: String) = peopleRepository.getPersonById(id)
}
