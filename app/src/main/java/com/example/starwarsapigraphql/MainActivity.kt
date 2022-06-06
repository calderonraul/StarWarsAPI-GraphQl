package com.example.starwarsapigraphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.starwarsapigraphql.presentation.PersonViewModel
import com.example.starwarsapigraphql.ui.theme.StarWarsAPIGraphQlTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsAPIGraphQlTheme {
                // A surface container using the 'background' color from the theme
                PersonListInit()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun PersonListInit(viewModel: PersonViewModel = hiltViewModel()){
    PersonList(state = viewModel.registerState)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StarWarsAPIGraphQlTheme {
        Greeting("Android")
    }
}