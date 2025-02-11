package com.jeezzzz.internshiptaskvillagewala.data.models.getPokemonList

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)