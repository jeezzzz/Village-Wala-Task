package com.jeezzzz.internshiptaskvillagewala.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeezzzz.internshiptaskvillagewala.data.models.getPokemonDetails.PokemonDetailsResponse
import com.jeezzzz.internshiptaskvillagewala.data.models.getPokemonList.PokemonResponse
import com.jeezzzz.internshiptaskvillagewala.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<PokemonResponse?>(null)
    val pokemonList: StateFlow<PokemonResponse?> = _pokemonList

    private val _pokemonDetail = MutableStateFlow<PokemonDetailsResponse?>(null)
    val pokemonDetail: StateFlow<PokemonDetailsResponse?> = _pokemonDetail

    fun fetchPokemonList() {
        viewModelScope.launch {
            _pokemonList.value = repository.getPokemonList()
        }
    }

    fun fetchPokemonDetail(id: Int) {
        viewModelScope.launch {
            _pokemonDetail.value = repository.getPokemonDetail(id)
        }
    }
}
