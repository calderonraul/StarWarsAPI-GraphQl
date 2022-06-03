package com.example.data.mappers.personsMapper

import com.example.data.mappers.EntityMapper
import com.example.data.model.Homeworld
import com.example.data.model.Person
import com.example.domain.entity.PersonDomain

class PersonMapper : EntityMapper<Person, PersonDomain> {
    override fun mapFromEntity(entity: Person): PersonDomain {
        return PersonDomain(
            id = entity.name,
            name = entity.name,
            homeworld = entity.homeworld?.let { HomeworldMapper().mapFromEntity(it) },
            species = entity.species?.let { SpeciesMapper().mapFromEntity(it) }
        )
    }

    override fun mapToEntity(domainModel: PersonDomain): Person {
        return Person(
            id = domainModel.id,
            name = domainModel.name,
            homeworld = domainModel.homeworld?.let { HomeworldMapper().mapToEntity(it) },
            species = domainModel.species?.let { SpeciesMapper().mapToEntity(it) }
        )
    }

    fun fromEntityList(initial: List<Person>): List<PersonDomain> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<PersonDomain>): List<Person> {
        return initial.map { mapToEntity(it) }
    }
}