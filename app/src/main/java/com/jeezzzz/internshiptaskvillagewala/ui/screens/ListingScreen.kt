package com.jeezzzz.internshiptaskvillagewala.ui.screens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.jeezzzz.internshiptaskvillagewala.viewmodels.ViewModel

@Composable
fun PokemonListScreen(navController: NavController, viewModel: ViewModel) {
    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }
    val pokemonList = viewModel.pokemonList.collectAsState().value

    LazyColumn {
        pokemonList?.results?.let { list ->
            items(list) { pokemon ->
                val id:Int = (pokemon.url.trimEnd('/').split("/").last()).toInt()
                Card(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { navController.navigate("pokemon_detail/$id") }) {
                    Row(Modifier.padding(8.dp)) {
                        Text(text = pokemon.name)
                    }
                }
            }
        }
    }
}