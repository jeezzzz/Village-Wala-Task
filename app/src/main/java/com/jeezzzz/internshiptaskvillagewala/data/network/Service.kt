package com.jeezzzz.internshiptaskvillagewala.data.network

import com.jeezzzz.internshiptaskvillagewala.data.models.getPokemonDetails.PokemonDetailsResponse
import com.jeezzzz.internshiptaskvillagewala.data.models.getPokemonList.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int = 50): PokemonResponse

    @GET("pokemon")
    suspend fun getPokemonDetail(@Query("id") id: Int): PokemonDetailsResponse
}