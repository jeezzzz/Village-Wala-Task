package com.jeezzzz.internshiptaskvillagewala.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.jeezzzz.internshiptaskvillagewala.data.models.getPokemonDetails.PokemonDetailsResponse
import com.jeezzzz.internshiptaskvillagewala.ui.components.RotatingPokeball
import com.jeezzzz.internshiptaskvillagewala.viewmodels.ViewModel

@Composable
fun DetailScreen(pokemonId: Int, viewModel: ViewModel) {
    var pokemonDetail by remember { mutableStateOf<PokemonDetailsResponse?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    Log.d("details", pokemonId.toString())

    // Fetch Pokémon Details
    LaunchedEffect(pokemonId) {
        viewModel.fetchPokemonDetail(pokemonId)
    }

    val fetchedPokemonDetail = viewModel.pokemonDetail.collectAsState().value

    // Update UI when data is fetched
    LaunchedEffect(fetchedPokemonDetail) {
        if (fetchedPokemonDetail != null) {
            pokemonDetail = fetchedPokemonDetail
            Log.d("details", pokemonDetail.toString())
            isLoading = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            // Show Rotating Poké Ball
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                RotatingPokeball() // Reusing existing rotating Poké Ball
            }
        } else if (pokemonDetail == null) {
            // Error Message if Data is Null
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Failed to load Pokémon details.", color = Color.Red, fontSize = 18.sp)
            }
        } else {
            // Show Pokémon Details
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                val imageUrl = pokemonDetail?.sprites?.other?.home?.front_default
                    ?: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$pokemonId.png"

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Pokémon Image (Left)
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = pokemonDetail?.name ?: "Pokemon Image",
                            modifier = Modifier
                                .size(120.dp)
                                .padding(end = 16.dp)
                        )

                        // Pokémon Name & Basic Info (Right)
                        Column {
                            Text(
                                text = pokemonDetail?.name?.replaceFirstChar { it.uppercase() }
                                    ?: "Unknown Pokémon",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "Height: ${pokemonDetail!!.height}", fontSize = 16.sp)
                            Text(text = "Weight: ${pokemonDetail!!.weight}", fontSize = 16.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Moves Card
                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .border(2.dp, Color(0xffFFCC00), RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Moves",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFB1B1B) // Updated Heading Color
                            )
                            val moves =
                                pokemonDetail?.moves?.map { it.move.name }?.take(5) ?: emptyList()
                            if (moves.isNotEmpty()) {
                                moves.forEach { move ->
                                    Text(text = "  - $move", fontSize = 16.sp)
                                }
                            } else {
                                Text(text = "  No moves available", fontSize = 16.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Abilities Card
                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .border(2.dp, Color(0xffFFCC00), RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Abilities",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFB1B1B) // Updated Heading Color
                            )
                            val abilities =
                                pokemonDetail?.abilities?.map { it.ability.name }?.take(5)
                                    ?: emptyList()
                            if (abilities.isNotEmpty()) {
                                abilities.forEach { ability ->
                                    Text(text = "  - $ability", fontSize = 16.sp)
                                }
                            } else {
                                Text(text = "  No abilities available", fontSize = 16.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Stats Card
                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .border(2.dp, Color(0xffFFCC00), RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Stats",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFB1B1B) // Updated Heading Color
                            )
                            val stats = pokemonDetail?.stats ?: emptyList()
                            if (stats.isNotEmpty()) {
                                stats.forEach { stat ->
                                    Text(
                                        text = "  ${stat.stat.name.replaceFirstChar { it.uppercase() }}: ${stat.base_stat}",
                                        fontSize = 16.sp
                                    )
                                }
                            } else {
                                Text(text = "  No stats available", fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
