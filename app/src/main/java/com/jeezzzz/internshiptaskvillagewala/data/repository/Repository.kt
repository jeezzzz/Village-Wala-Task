package com.jeezzzz.internshiptaskvillagewala.data.repository

import com.jeezzzz.internshiptaskvillagewala.data.models.getPokemonDetails.PokemonDetailsResponse
import com.jeezzzz.internshiptaskvillagewala.data.models.getPokemonList.PokemonResponse
import com.jeezzzz.internshiptaskvillagewala.data.network.Service
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: Service
) {

    suspend fun getPokemonList(): PokemonResponse = apiService.getPokemonList()

    suspend fun getPokemonDetail(id: Int): PokemonDetailsResponse = apiService.getPokemonDetail(id)
}
