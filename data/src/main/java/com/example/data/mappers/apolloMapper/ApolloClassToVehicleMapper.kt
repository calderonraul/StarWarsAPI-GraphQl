package com.example.data.mappers.apolloMapper

import com.example.data.PersonDetailQuery
import com.example.data.mappers.EntityMapper
import com.example.data.model.personDetail.Vehicle

class ApolloClassToVehicleMapper:EntityMapper<Vehicle,PersonDetailQuery.Vehicle> {
    override fun mapFromEntity(entity: Vehicle): PersonDetailQuery.Vehicle {
        return PersonDetailQuery.Vehicle(
            name=entity.name
        )
    }

    override fun mapToEntity(domainModel: PersonDetailQuery.Vehicle): Vehicle {
        return Vehicle(
            name=domainModel.name
        )
    }

    fun mapFromEntityList(entityList: List<Vehicle?>?): List<PersonDetailQuery.Vehicle?>? {
        return entityList?.map { it?.let { it1 -> mapFromEntity(it1) } }
    }

    fun mapToEntityList(domainModelList: List<PersonDetailQuery.Vehicle?>?): List<Vehicle> {
        return domainModelList!!.map { mapToEntity(it!!) }
    }
}