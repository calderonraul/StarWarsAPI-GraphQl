package com.example.domain.repository

import com.example.domain.entity.PersonDomain
import kotlinx.coroutines.flow.Flow


interface PersonsRepository {
    suspend fun getAllPersonsAPI()
    fun getPersonsFromRoom():Flow<List<PersonDomain>>
}