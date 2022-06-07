package com.example.data.mappers.personsMapper

import com.example.data.mappers.EntityMapper
import com.example.data.model.allPeople.Species
import com.example.domain.entity.allPeople.SpeciesDomain

class SpeciesMapper : EntityMapper<Species, SpeciesDomain> {
    override fun mapFromEntity(entity: Species): SpeciesDomain {
        return SpeciesDomain(
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: SpeciesDomain): Species {
        return Species(
            name = domainModel.name
        )
    }
}