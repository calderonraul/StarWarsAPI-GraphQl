package com.example.starwarsapigraphql

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.domain.entity.allPeople.PersonDomain
import com.example.domain.entity.personDetail.PersonXDomain
import com.example.domain.entity.personDetail.VehicleConnectionDomain
import com.example.domain.entity.personDetail.VehicleDomain
import com.example.starwarsapigraphql.presentation.PersonUIState
import com.example.starwarsapigraphql.ui.theme.StarWarsAPIGraphQlTheme
import kotlinx.coroutines.flow.Flow


@Composable
fun PersonItem(person: PersonDomain,  navController: NavController) {

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
fun PersonList(personList: Flow<PagingData<PersonDomain>>, navController: NavController) {
    val personListItems:LazyPagingItems<PersonDomain> = personList.collectAsLazyPagingItems()
    LazyColumn{
        items(items = personListItems) { item ->
            if (item != null) {
                PersonItem(item, navController = navController)
            }
        }
    }
}


@Composable
fun PersonDetail(personX: PersonXDomain) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .weight(0.2f)
                .padding(16.dp),
            text = "General Information",
            style = typography.subtitle1,
            fontWeight = FontWeight.Bold,
        )

        Row(Modifier.weight(0.2f)) {
            Text(text = "Eye Color:  ", modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.weight(0.8f))
            Text(
                text = "${personX.eyeColor}",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }
        Row(Modifier.weight(0.2f)) {
            Text(text = "Hair Color:  ", modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.weight(0.8f))
            Text(
                text = "${personX.hairColor}",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }
        Row(Modifier.weight(0.2f)) {
            Text(text = "Skin Color:  ", modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.weight(0.8f))
            Text(
                text = "${personX.skinColor}",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }
        Row(Modifier.weight(0.2f)) {
            Text(text = "Birth Year: ", modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.weight(0.8f))
            Text(
                text = "${personX.birthYear}",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun VehicleItem(vehicle: VehicleDomain) {
    Column {
        vehicle.name?.let { Text(text = it, modifier = Modifier.padding(16.dp)) }
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
        TopAppBar(
            title = {
                Text(
                    text = "${personX.name}"
                )
            },
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth()
        )
        Row(modifier = Modifier.weight(0.4f)) {
            PersonDetail(personX = personX)
        }
        Row(modifier = Modifier.weight(0.4f)) {
            if (personX.vehicleConnection == null || personX.vehicleConnection!!.vehicles?.isEmpty() != false) {
                Text(
                    text = "No vehicles",
                    modifier = Modifier.padding(16.dp),
                    style = typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Column() {
                    Text(
                        text = "Vehicles",
                        modifier = Modifier.padding(16.dp),
                        style = typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    VehicleList(vehicleList = personX.vehicleConnection?.vehicles!!)
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemPreview() {

    val personX = PersonXDomain(
        id = "1",
        name = "Luke Skywalker",
        birthYear = "19BBY",

        eyeColor = "blue",
        hairColor = "blond",
        skinColor = "fair",
        vehicleConnection = VehicleConnectionDomain(
            vehicles = listOf(
                VehicleDomain(
                    name = "Sand Crawler"
                ),
                VehicleDomain(
                    name = "T-16 skyhopper"
                )
            )
        )
    )

    StarWarsAPIGraphQlTheme {
        PersonDetail(personX = personX)
    }
}