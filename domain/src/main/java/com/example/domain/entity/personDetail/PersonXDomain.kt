package com.example.domain.entity.personDetail

data class PersonXDomain(
    val id: String,
    val birthYear: String?,
    val eyeColor: String?,
    val hairColor: String?,
    val name: String?,
    val skinColor: String?,
    val vehicleConnection: VehicleConnectionDomain?
)
