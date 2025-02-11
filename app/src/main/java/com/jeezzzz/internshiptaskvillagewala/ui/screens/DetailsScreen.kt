package com.jeezzzz.internshiptaskvillagewala.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.jeezzzz.internshiptaskvillagewala.viewmodels.ViewModel

@Composable
fun DetailScreen(pokemonId: Int, viewModel: ViewModel) {
    LaunchedEffect(Unit) {
        viewModel.fetchPokemonDetail(pokemonId) // Call the function when screen loads
    }
    val pokemonDetail = viewModel.pokemonDetail.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        pokemonDetail?.let {
            Image(
                painter = rememberImagePainter(it.sprites.back_female),
                contentDescription = null
            )
            Text(text = "Name: ${it.name}")
            Text(text = "Height: ${it.height}")
            Text(text = "Weight: ${it.weight}")
        }
    }
}
