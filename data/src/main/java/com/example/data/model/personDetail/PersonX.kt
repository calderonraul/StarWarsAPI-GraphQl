package com.example.data.model.personDetail

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "person_detail")
data class PersonX(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val birthYear: String?,
    val eyeColor: String?,
    val hairColor: String?,
    val name: String?,
    val skinColor: String?,
    val vehicleConnection: VehicleConnection?
)