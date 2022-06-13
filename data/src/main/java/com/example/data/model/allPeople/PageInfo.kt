package com.example.data.model.allPeople

data class PageInfo(
    val endCursor: String?,
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean
)