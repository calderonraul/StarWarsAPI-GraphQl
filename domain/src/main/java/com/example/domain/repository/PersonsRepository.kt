package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.entity.allPeople.AllPeopleDomain
import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.entity.personDetail.PersonXDomain
import kotlinx.coroutines.flow.Flow


interface PersonsRepository {
    suspend fun getAllPersonsAPI(numberOfItems:Int,cursor:String): AllPeopleDomain?
    fun getPersonsFromRoom():Flow<List<PersonDomain>>
    suspend fun getPersonById(id:String)
    fun getPersonByIdFromRoom():Flow<PersonXDomain>
    fun getOrdersPaginated(): Flow<PagingData<PersonDomain>>

}