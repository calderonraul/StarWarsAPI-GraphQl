package com.example.data


import android.util.Log
import com.apollographql.apollo3.api.Optional
import com.example.data.api.PersonsAPI
import com.example.data.mappers.apolloMapper.ApolloClassToModelMapper
import com.example.data.mappers.personsMapper.PersonMapper
import com.example.domain.repository.PersonsRepository
import com.example.data.database.PersonsDao
import com.example.data.mappers.apolloMapper.ApolloClassAllPeopleMapper
import com.example.data.mappers.apolloMapper.ApolloClassPersonXMapper
import com.example.data.mappers.personDetailMapper.PersonDetailMapper
import com.example.data.mappers.personsMapper.AllPeopleMapper
import com.example.data.model.allPeople.AllPeople
import com.example.domain.entity.allPeople.AllPeopleDomain
import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.entity.personDetail.PersonXDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class PeopleRepositoryImpl(
    private val dao: PersonsDao,
    private val personMapper: PersonMapper,
    private val api: PersonsAPI,
    private val apolloMapper: ApolloClassToModelMapper,
    private val personXMapperApollo: ApolloClassPersonXMapper,
    private val personXMapper: PersonDetailMapper
) :
    PersonsRepository {

    override suspend fun getAllPersonsAPI(numberOfItems:Int,cursor:String): AllPeopleDomain? {
        val auxItem:Optional<Int>
        auxItem = Optional.Present(numberOfItems)
        val auxCursor:Optional<String>
        auxCursor = Optional.Present(cursor)
        val listAux = api.getApolloClient().query(AllPeopleFromApiQuery(auxItem,auxCursor)).execute().data
        if (listAux != null) {
            dao.deleteTable()
            dao.insertAllPersons(apolloMapper.toEntityList(listAux.allPeople?.people))
        }
        val allPeople= listAux?.allPeople?.let { ApolloClassAllPeopleMapper().mapToEntity(it) }

        return allPeople?.let { AllPeopleMapper().mapFromEntity(it) }
    }

    override fun getPersonsFromRoom(): Flow<List<PersonDomain>> {
        return dao.getPersonsFromRoom().map {
            personMapper.fromEntityList(it)
        }
    }

    override suspend fun getPersonById(id: String) {
        val aux = api.getApolloClient().query(PersonDetailQuery(id)).execute().data
        if (aux != null) {
            dao.deletePersonDetailTable()
            dao.insertPersonDetail(personXMapperApollo.mapToEntity(aux.person))
        }
    }

    override fun getPersonByIdFromRoom(): Flow<PersonXDomain> {
        return dao.getPersonDetailFromRoom().map {
            personXMapper.mapFromEntity(it)
        }
    }


}