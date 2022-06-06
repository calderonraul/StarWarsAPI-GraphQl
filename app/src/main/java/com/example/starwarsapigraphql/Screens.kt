package com.example.starwarsapigraphql

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.entity.HomeWorldDomain
import com.example.domain.entity.PersonDomain
import com.example.starwarsapigraphql.presentation.PersonUIState
import com.example.starwarsapigraphql.presentation.PersonViewModel
import com.example.starwarsapigraphql.ui.theme.StarWarsAPIGraphQlTheme



@Composable
fun PersonItem(person: PersonDomain) {
    var aux= ""
    Row(Modifier.padding(16.dp)) {

        Column(modifier = Modifier.weight(1f)) {
            person.name?.let { Text(text = it, style = typography.subtitle1) }
            aux = if (person.species?.name==null){
                "Human"
            }else{
                person.species!!.name!!
            }
            person.homeworld?.name?.let { Text(text = "$aux from $it", style = MaterialTheme.typography.body2) }

        }
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
            contentDescription = null
        )
    }
}

@Composable
fun PersonList(state: PersonUIState) {
    state.fetchMoreData()
    val personList by state.personFlow.collectAsState()
    LazyColumn() {
        itemsIndexed(personList) { _, item ->
            PersonItem(item)
        }
    }
}



@Preview
@Composable
fun ItemPreview() {

    val testHomeWorldDomain = HomeWorldDomain(
        "naboo"
    )

    val personita = PersonDomain(
        "69",
        "Luke Skywalker",
        null,
        testHomeWorldDomain
    )

    StarWarsAPIGraphQlTheme {
        PersonItem(person = personita)
    }
}