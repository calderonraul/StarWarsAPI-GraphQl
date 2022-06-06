package com.example.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons_table")
data class Person(

    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String?,
    val species: Species? = null,
    val homeworld: Homeworld?
)
