package com.example.data.mappers.apolloMapper

import com.example.data.PersonDetailQuery
import com.example.data.mappers.EntityMapper
import com.example.data.model.personDetail.PersonX

class ApolloClassPersonXMapper : EntityMapper<PersonX, PersonDetailQuery.Person?> {
    override fun mapFromEntity(entity: PersonX): PersonDetailQuery.Person {
        return PersonDetailQuery.Person(
            id = entity.id,
            name = entity.name,
            eyeColor = entity.eyeColor,
            hairColor = entity.hairColor,
            skinColor = entity.skinColor,
            birthYear = entity.birthYear,
            vehicleConnection = entity.vehicleConnection?.let {
                ApolloClassToVehicleConnectionMapper().mapFromEntity(
                    it
                )
            }
        )
    }

    override fun mapToEntity(domainModel: PersonDetailQuery.Person?): PersonX {
        return PersonX(
            id = domainModel?.id ?: "",
            name = domainModel?.name,
            eyeColor = domainModel?.eyeColor,
            hairColor = domainModel?.hairColor,
            skinColor = domainModel?.skinColor,
            birthYear = domainModel?.birthYear,
            vehicleConnection = domainModel?.vehicleConnection?.let {
                ApolloClassToVehicleConnectionMapper().mapToEntity(
                    it
                )
            }
        )
    }
}