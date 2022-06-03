package com.example.starwarsapigraphql.domain.entity

import com.example.domain.entity.PersonDomain

data class AllPeopleDomain(
    val people: List<PersonDomain>
)
