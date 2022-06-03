package com.example.data.mappers.personsMapper

import com.example.data.mappers.EntityMapper
import com.example.data.model.Homeworld
import com.example.domain.entity.HomeWorldDomain

class HomeworldMapper : EntityMapper<Homeworld, HomeWorldDomain> {
    override fun mapFromEntity(entity: Homeworld): HomeWorldDomain {
        return HomeWorldDomain(
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: HomeWorldDomain): Homeworld {
        return Homeworld(
            name = domainModel.name
        )
    }
}