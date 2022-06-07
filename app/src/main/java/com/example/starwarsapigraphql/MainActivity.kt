package com.example.starwarsapigraphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.starwarsapigraphql.presentation.PersonViewModel
import com.example.starwarsapigraphql.ui.theme.StarWarsAPIGraphQlTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsAPIGraphQlTheme {
                // A surface container using the 'background' color from the theme
                StartApp()
            }
        }
    }
}

@Composable
fun PersonListInit(viewModel: PersonViewModel = hiltViewModel(), navController: NavController) {
    PersonList(state = viewModel.registerState, navController)
}

@Composable
fun PersonDetailInit(viewModel: PersonViewModel = hiltViewModel(),aux:String) {
    PersonDetailScreen(state = viewModel.registerState,aux)
}

@Composable
fun StartApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "PersonListInit") {
        composable("PersonListInit") {
            PersonListInit(navController = navController)
        }
        composable("PersonDetailInit/{id}", listOf(navArgument("id"){
            type= NavType.StringType
        })) {
            val aux=it.arguments?.getString("id")
            if (aux != null) {
                PersonDetailInit(aux = aux)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StarWarsAPIGraphQlTheme {
    }
}