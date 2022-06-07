package com.example.data.mappers.personDetailMapper

import com.example.data.mappers.EntityMapper
import com.example.data.model.personDetail.PersonX
import com.example.domain.entity.personDetail.PersonXDomain

class PersonDetailMapper : EntityMapper<PersonX?, PersonXDomain> {
    override fun mapFromEntity(entity: PersonX?): PersonXDomain {
        return PersonXDomain(
            id = entity?.id ?: "",
            name = entity?.name,
            eyeColor = entity?.eyeColor,
            hairColor = entity?.hairColor,
            skinColor = entity?.skinColor,
            birthYear = entity?.birthYear,
            vehicleConnection = entity?.vehicleConnection?.let {
                VehicleConnectionMapper().mapFromEntity(
                    it
                )
            },
        )
    }

    override fun mapToEntity(domainModel: PersonXDomain): PersonX {
        return PersonX(
            id = domainModel.id,
            name = domainModel.name,
            eyeColor = domainModel.eyeColor,
            hairColor = domainModel.hairColor,
            skinColor = domainModel.skinColor,
            birthYear = domainModel.birthYear,
            vehicleConnection = domainModel.vehicleConnection?.let {
                VehicleConnectionMapper().mapToEntity(
                    it
                )
            },
        )
    }
}