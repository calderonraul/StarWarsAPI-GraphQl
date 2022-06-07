package com.example.data.mappers.personDetailMapper

import com.example.data.mappers.EntityMapper
import com.example.data.model.personDetail.Vehicle
import com.example.domain.entity.personDetail.VehicleDomain

class VehicleMapper : EntityMapper<Vehicle, VehicleDomain> {
    override fun mapFromEntity(entity: Vehicle): VehicleDomain {
        return VehicleDomain(
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: VehicleDomain): Vehicle {
        return Vehicle(
            name = domainModel.name
        )
    }

    fun fromEntityList(initial: List<Vehicle?>?): List<VehicleDomain?>? {
        return initial?.map { it?.let { it1 -> mapFromEntity(it1) } }
    }

    fun toEntityList(initial: List<VehicleDomain?>?): List<Vehicle?>? {
        return initial?.map { it?.let { it1 -> mapToEntity(it1) } }
    }
}