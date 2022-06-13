package com.example.data.mappers.personsMapper

import com.example.data.mappers.EntityMapper
import com.example.data.model.allPeople.AllPeople
import com.example.domain.entity.allPeople.AllPeopleDomain

class AllPeopleMapper : EntityMapper<AllPeople, AllPeopleDomain> {
    override fun mapFromEntity(entity: AllPeople): AllPeopleDomain {
        return AllPeopleDomain(
            people = PersonMapper().fromEntityList(entity.people),
            pageInfoDomain = PageInfoMapper().mapFromEntity(entity.pageInfo)
        )
    }

    override fun mapToEntity(domainModel: AllPeopleDomain): AllPeople {
        return AllPeople(
            people = PersonMapper().toEntityList(domainModel.people),
            pageInfo = PageInfoMapper().mapToEntity(domainModel.pageInfoDomain)
        )
    }
}