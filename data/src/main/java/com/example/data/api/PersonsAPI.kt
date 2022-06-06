package com.example.data.api

import com.apollographql.apollo3.ApolloClient

class PersonsAPI {
    fun getApolloClient():ApolloClient{
        return ApolloClient.Builder()
            .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
            .build()
    }
}