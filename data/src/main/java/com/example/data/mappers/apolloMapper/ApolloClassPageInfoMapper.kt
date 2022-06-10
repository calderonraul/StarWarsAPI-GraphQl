package com.example.data.mappers.apolloMapper

import com.example.data.AllPeopleFromApiQuery
import com.example.data.mappers.EntityMapper
import com.example.data.model.allPeople.PageInfo


class ApolloClassPageInfoMapper: EntityMapper<PageInfo,AllPeopleFromApiQuery.PageInfo> {
    override fun mapFromEntity(entity: PageInfo): AllPeopleFromApiQuery.PageInfo {
        return AllPeopleFromApiQuery.PageInfo(
            hasNextPage = entity.hasNextPage,
            endCursor = entity.endCursor
        )
    }

    override fun mapToEntity(domainModel: AllPeopleFromApiQuery.PageInfo): PageInfo {
        return PageInfo(
            hasNextPage = domainModel.hasNextPage,
            endCursor = domainModel.endCursor
        )
    }
}