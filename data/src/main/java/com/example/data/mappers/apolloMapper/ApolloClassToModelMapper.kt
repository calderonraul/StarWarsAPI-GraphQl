package com.example.data.mappers.apolloMapper

import com.example.data.AllPeopleFromApiQuery
import com.example.data.mappers.EntityMapper
import com.example.data.model.Person

class ApolloClassToModelMapper : EntityMapper<Person, AllPeopleFromApiQuery.Person> {
    override fun mapFromEntity(entity: Person): AllPeopleFromApiQuery.Person {
        return AllPeopleFromApiQuery.Person(
            id = entity.id!!,
            homeworld = entity.homeworld?.let { ApolloHomeworldMapper().mapFromEntity(it) },
            name = entity.name,
            species = entity.species?.let { ApolloSpeciesToSpecies().mapFromEntity(it) }
        )
    }

    override fun mapToEntity(domainModel: AllPeopleFromApiQuery.Person): Person {
        return Person(
            id = domainModel.id,
            name = domainModel.name,
            homeworld = domainModel.homeworld?.let { ApolloHomeworldMapper().mapToEntity(it) },
            species = domainModel.species?.let { ApolloSpeciesToSpecies().mapToEntity(it) }
        )

    }


    fun fromEntityList(initial: List<Person>): List<AllPeopleFromApiQuery.Person> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<AllPeopleFromApiQuery.Person?>?): List<Person> {
        return initial!!.map { mapToEntity(it!!) }
    }


}