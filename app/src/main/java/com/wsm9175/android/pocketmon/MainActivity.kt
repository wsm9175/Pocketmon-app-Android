package com.wsm9175.android.pocketmon

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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wsm9175.android.pocketmon.screen.DetailScreen
import com.wsm9175.android.pocketmon.screen.MainScreen
import com.wsm9175.android.pocketmon.ui.theme.PocketmonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketmonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TopLevel()
                }
            }
        }
    }
}

@Composable
fun TopLevel(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        "Home",
        modifier = Modifier,
    ){
        composable("Home"){
            MainScreen(
                onPokemonClick ={
                    val pokemonId = it.substringAfter("pokemon/")
                        .substringBefore("/")
                        .toInt()
                    navController.navigate("Detail/${pokemonId}")
                }
            )
        }

        composable(
            "Detail/{pokemonId}",
            arguments = listOf(
                navArgument("pokemonId"){
                    type = NavType.IntType
                }
            )
        ){
            val pokemonId = it.arguments?.getInt("pokemonId") as Int
            DetailScreen(
                pokemonId = pokemonId,
                onUpButtonClick = {
                    navController.navigate("Home"){
                        popUpTo("Home"){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PocketmonTheme {
        TopLevel()
    }
}