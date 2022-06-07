package com.example.data.mappers.apolloMapper

import com.example.data.AllPeopleFromApiQuery
import com.example.data.mappers.EntityMapper
import com.example.data.model.allPeople.Homeworld

class ApolloHomeworldMapper : EntityMapper<Homeworld, AllPeopleFromApiQuery.Homeworld> {
    override fun mapFromEntity(entity: Homeworld): AllPeopleFromApiQuery.Homeworld {
        return AllPeopleFromApiQuery.Homeworld(
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: AllPeopleFromApiQuery.Homeworld): Homeworld {
        return Homeworld(
            name = domainModel.name
        )
    }
}