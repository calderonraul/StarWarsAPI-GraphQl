package com.example.data.mappers.apolloMapper

import com.example.data.AllPeopleFromApiQuery
import com.example.data.mappers.EntityMapper
import com.example.data.model.allPeople.AllPeople

class ApolloClassAllPeopleMapper:EntityMapper<AllPeople, AllPeopleFromApiQuery.AllPeople> {
    override fun mapFromEntity(entity: AllPeople): AllPeopleFromApiQuery.AllPeople {
        return AllPeopleFromApiQuery.AllPeople(
            people = ApolloClassToModelMapper().fromEntityList(entity.people),
            pageInfo = ApolloClassPageInfoMapper().mapFromEntity(entity.pageInfo)
        )
    }

    override fun mapToEntity(domainModel: AllPeopleFromApiQuery.AllPeople): AllPeople {
        return AllPeople(
            people = ApolloClassToModelMapper().toEntityList(domainModel.people),
            pageInfo = ApolloClassPageInfoMapper().mapToEntity(domainModel.pageInfo)
        )
    }
}