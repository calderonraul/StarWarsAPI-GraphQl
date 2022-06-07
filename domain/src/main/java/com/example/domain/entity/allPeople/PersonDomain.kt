package com.example.domain.entity.allPeople

data class PersonDomain(
    val id: String?,
    val name: String?,
    val species: SpeciesDomain? = null,
    val homeworld: HomeWorldDomain?
)
