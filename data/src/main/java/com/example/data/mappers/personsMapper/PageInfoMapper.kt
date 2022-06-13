package com.example.data.mappers.personsMapper

import com.example.data.mappers.EntityMapper
import com.example.data.model.allPeople.PageInfo
import com.example.domain.entity.allPeople.PageInfoDomain

class PageInfoMapper : EntityMapper<PageInfo, PageInfoDomain> {
    override fun mapFromEntity(entity: PageInfo): PageInfoDomain {
        return PageInfoDomain(
            endCursor = entity.endCursor,
            hasNextPage = entity.hasNextPage,
            hasPreviousPage = entity.hasPreviousPage
        )
    }

    override fun mapToEntity(domainModel: PageInfoDomain): PageInfo {
        return PageInfo(
            endCursor = domainModel.endCursor,
            hasNextPage = domainModel.hasNextPage,
            hasPreviousPage = domainModel.hasPreviousPage
        )
    }
}