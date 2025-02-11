package com.jeezzzz.internshiptaskvillagewala.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jeezzzz.internshiptaskvillagewala.R
import com.jeezzzz.internshiptaskvillagewala.ui.components.PokemonCard
import com.jeezzzz.internshiptaskvillagewala.ui.components.RotatingPokeball
import com.jeezzzz.internshiptaskvillagewala.viewmodels.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingScreen(navController: NavController, viewModel: ViewModel) {
    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }

    val pokemonResponse by viewModel.pokemonList.collectAsState()
    val pokemonList = pokemonResponse?.results ?: emptyList()

    var isLoading by remember { mutableStateOf(true) }
    LaunchedEffect(pokemonResponse) {
        if (pokemonResponse != null) isLoading = false
    }

    Column {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(10.dp)
                .height(60.dp),
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Pokemon Logo",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(10.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFFB1B1B))
        )

        AnimatedVisibility(visible = isLoading, modifier = Modifier.fillMaxSize()) {
            RotatingPokeball()
        }

        if (!isLoading) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp),
                modifier = Modifier.padding(12.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(pokemonList.size) { index ->
                    val pokemon = pokemonList[index]
                    PokemonCard(navController = navController, pokemon = pokemon)
                }
            }
        }
    }
}

