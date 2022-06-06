package com.example.data

import com.apollographql.apollo3.ApolloClient
import com.example.data.api.PersonsAPI
import com.example.data.mappers.apolloMapper.ApolloClassToModelMapper
import com.example.data.mappers.personsMapper.PersonMapper
import com.example.domain.repository.PersonsRepository
import com.example.starwarsapigraphql.data.database.PersonsDao
import com.example.domain.entity.PersonDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PeopleRepositoryImpl(
    private val dao: PersonsDao,
    private val personMapper: PersonMapper,
    private val api: PersonsAPI,
    private val apolloMapper: ApolloClassToModelMapper
) :
    PersonsRepository {


    override suspend fun getAllPersonsAPI() {
        val listAux = api.getApolloClient().query(AllPeopleFromApiQuery()).execute().data
        if (listAux != null) {
            dao.insertAllPersons(apolloMapper.toEntityList(listAux.allPeople?.people))
        }

    }

    override fun getPersonsFromRoom(): Flow<List<PersonDomain>> {
        return dao.getPersonsFromRoom().map {
            personMapper.fromEntityList(it)
        }
    }

}