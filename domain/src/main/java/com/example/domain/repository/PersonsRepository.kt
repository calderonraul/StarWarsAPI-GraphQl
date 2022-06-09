package com.example.domain.repository

import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.entity.personDetail.PersonXDomain
import kotlinx.coroutines.flow.Flow


interface PersonsRepository {
    suspend fun getAllPersonsAPI(numberOfItems:Int):List<PersonDomain>
    fun getPersonsFromRoom():Flow<List<PersonDomain>>
    suspend fun getPersonById(id:String)
    fun getPersonByIdFromRoom():Flow<PersonXDomain>

}