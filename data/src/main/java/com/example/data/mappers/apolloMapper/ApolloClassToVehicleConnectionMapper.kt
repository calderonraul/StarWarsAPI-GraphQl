package com.example.data.mappers.apolloMapper

import com.example.data.PersonDetailQuery
import com.example.data.mappers.EntityMapper
import com.example.data.mappers.personDetailMapper.VehicleMapper
import com.example.data.model.personDetail.VehicleConnection

class ApolloClassToVehicleConnectionMapper:EntityMapper<VehicleConnection,PersonDetailQuery.VehicleConnection> {
    override fun mapFromEntity(entity: VehicleConnection): PersonDetailQuery.VehicleConnection {
        return PersonDetailQuery.VehicleConnection(
            vehicles = ApolloClassToVehicleMapper().mapFromEntityList(entity.vehicles)
        )
    }

    override fun mapToEntity(domainModel: PersonDetailQuery.VehicleConnection): VehicleConnection {
        return VehicleConnection(
            vehicles = ApolloClassToVehicleMapper().mapToEntityList(domainModel.vehicles)
        )
    }
}