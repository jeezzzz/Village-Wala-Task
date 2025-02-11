package com.jeezzzz.internshiptaskvillagewala.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jeezzzz.internshiptaskvillagewala.ui.screens.DetailScreen
import com.jeezzzz.internshiptaskvillagewala.ui.screens.PokemonListScreen
import com.jeezzzz.internshiptaskvillagewala.viewmodels.ViewModel

@Composable
fun Navigation(navController: NavHostController) {
    val viewModel: ViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "pokemon_list") {
        composable("pokemon_list") {
            PokemonListScreen(navController, viewModel)
        }
        composable("pokemon_detail/{pokemonId}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toIntOrNull()
            pokemonId?.let {
                DetailScreen(it, viewModel)
            }
        }
    }
}


