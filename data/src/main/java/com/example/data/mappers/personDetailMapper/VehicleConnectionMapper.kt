package com.example.data.mappers.personDetailMapper

import com.example.data.mappers.EntityMapper
import com.example.data.model.personDetail.VehicleConnection
import com.example.domain.entity.personDetail.VehicleConnectionDomain

class VehicleConnectionMapper:EntityMapper<VehicleConnection,VehicleConnectionDomain> {
    override fun mapFromEntity(entity: VehicleConnection): VehicleConnectionDomain {
        return VehicleConnectionDomain(
            vehicles = VehicleMapper().fromEntityList(entity.vehicles),
        )
    }

    override fun mapToEntity(domainModel: VehicleConnectionDomain): VehicleConnection {
        return VehicleConnection(
            vehicles = VehicleMapper().toEntityList(domainModel.vehicles),
        )
    }
}
