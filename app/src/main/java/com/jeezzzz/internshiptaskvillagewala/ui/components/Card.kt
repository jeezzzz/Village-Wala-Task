package com.jeezzzz.internshiptaskvillagewala.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jeezzzz.internshiptaskvillagewala.data.models.getPokemonList.Result

@Composable
fun PokemonCard(navController: NavController, pokemon: Result) {

    val id = pokemon.url.trimEnd('/').split("/").last().toIntOrNull() ?: 0
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(180.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = { navController.navigate("pokemon_detail/$id") }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"

            AsyncImage(
                model = imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier.size(100.dp),
                placeholder = painterResource(id = com.jeezzzz.internshiptaskvillagewala.R.drawable.pokeball), // Show Poké Ball before image loads
                error = painterResource(id = com.jeezzzz.internshiptaskvillagewala.R.drawable.pokeball) // If image fails, keep Poké Ball
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                color = Color.Black,
            )
        }
    }
}
