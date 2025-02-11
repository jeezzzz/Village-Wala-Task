package com.jeezzzz.internshiptaskvillagewala.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeezzzz.internshiptaskvillagewala.ui.screens.DetailScreen
import com.jeezzzz.internshiptaskvillagewala.ui.screens.ErrorScreen
import com.jeezzzz.internshiptaskvillagewala.ui.screens.ListingScreen
import com.jeezzzz.internshiptaskvillagewala.viewmodels.ViewModel

@Composable
fun Navigation(navController: NavHostController) {
    val viewModel: ViewModel = hiltViewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "pokemon_list") {
        composable("pokemon_list") {
            ListingScreen(navController, viewModel)
        }

        composable("pokemon_detail/{pokemonId}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toIntOrNull()

            if (pokemonId != null) {
                DetailScreen(pokemonId, viewModel)
            } else {
                Log.e("Navigation", "Invalid or missing pokemonId")
                ErrorScreen(navController, "Invalid Pokémon ID")
            }
        }
    }
}


