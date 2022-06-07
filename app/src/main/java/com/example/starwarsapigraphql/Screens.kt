package com.example.starwarsapigraphql

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.model.personDetail.PersonX
import com.example.domain.entity.allPeople.HomeWorldDomain
import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.entity.personDetail.PersonXDomain
import com.example.domain.entity.personDetail.VehicleDomain
import com.example.starwarsapigraphql.presentation.PersonUIState
import com.example.starwarsapigraphql.ui.theme.StarWarsAPIGraphQlTheme


@Composable
fun PersonItem(person: PersonDomain, state: PersonUIState, navController: NavController) {
    val word by state.wordValue.collectAsState()
    var aux = ""
    Row(modifier = Modifier
        .padding(16.dp)
        .clickable {
            val id = person.id
            navController.navigate("PersonDetailInit/${id}")
        }) {

        Column(modifier = Modifier.weight(1f)) {
            person.name?.let {
                Text(
                    text = it,
                    style = typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
            }
            aux = if (person.species?.name == null) {
                "Human"
            } else {
                person.species!!.name!!
            }
            person.homeworld?.name?.let {
                Text(
                    text = "$aux from $it",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(vertical = 5.dp),
                    color = Color.Gray
                )
            }
            Divider(
                color = Color.Gray,
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 15.dp)
            )

        }
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
            contentDescription = null
        )
    }
}

@Composable
fun PersonList(state: PersonUIState, navController: NavController) {
    state.fetchMoreData()
    val personList by state.personFlow.collectAsState()



    Column() {
        TopAppBar(
            title = { Text(text = "People of Star Wars") },
            modifier = Modifier.weight(0.1f)
        )
        LazyColumn(modifier = Modifier.weight(0.9f)) {
            itemsIndexed(personList) { _, item ->
                PersonItem(item, state, navController)
            }
        }
    }


}

@Composable
fun PersonDetail(personX: PersonXDomain) {
    Column {
        Text(
            text = "General Information",
            style = typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Eye Color:  ${personX.eyeColor}")
        Text(text = "Hair Color:  ${personX.hairColor}")
        Text(text = "Skin Color: ${personX.skinColor}")
        Text(text = "Birth Year: ${personX.birthYear}")
    }
}


@Composable
fun VehicleItem(vehicle: VehicleDomain) {
    Column {
        vehicle.name?.let { Text(text = it) }
    }
}

@Composable
fun VehicleList(vehicleList: List<VehicleDomain?>) {
    Column {
        vehicleList.forEach {
            if (it != null) {
                VehicleItem(it)
            }
        }
    }
}


@Composable
fun PersonDetailScreen(state: PersonUIState, aux: String) {
    val personX by state.personDetailFlow.collectAsState()
    state.onWordValueChanged(aux)
    state.fetchPersonDetail()

    Column() {
        PersonDetail(personX = personX)
        if (personX.vehicleConnection == null) {
            Text(text = "No vehicles")
        } else {
            VehicleList(vehicleList = personX.vehicleConnection?.vehicles!!)
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

    }
}