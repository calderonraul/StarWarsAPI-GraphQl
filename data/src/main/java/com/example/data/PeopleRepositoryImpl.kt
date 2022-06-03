package com.example.data

import com.apollographql.apollo3.ApolloClient
import com.example.data.mappers.apolloMapper.ApolloClassToModelMapper
import com.example.data.mappers.personsMapper.PersonMapper
import com.example.domain.repository.PersonsRepository
import com.example.starwarsapigraphql.data.database.PersonsDao
import com.example.domain.entity.PersonDomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PeopleRepositoryImpl(private val dao: PersonsDao, val personMapper: PersonMapper) :
    PersonsRepository {

    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
        .build()

    override suspend fun getAllPersonsAPI() {
        val listAux = apolloClient.query(AllPeopleFromApiQuery()).execute().data
        if (listAux != null) {
            dao.insertAllPersons(ApolloClassToModelMapper().toEntityList(listAux.allPeople?.people))
        }

    }

    override fun getPersonsFromRoom(): Flow<List<PersonDomain>> {
        return dao.getPersonsFromRoom().map {
            personMapper.fromEntityList(it)
        }
    }


}