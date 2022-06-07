package com.example.data.mappers.apolloMapper

import com.example.data.AllPeopleFromApiQuery
import com.example.data.mappers.EntityMapper
import com.example.data.model.allPeople.Species

class ApolloSpeciesToSpecies : EntityMapper<Species,AllPeopleFromApiQuery.Species>{
    override fun mapFromEntity(entity: Species): AllPeopleFromApiQuery.Species {
        return AllPeopleFromApiQuery.Species(
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: AllPeopleFromApiQuery.Species): Species {
        return Species(
            name = domainModel.name
        )
    }
}