package com.wsm9175.android.pocketmon.domain

data class PokemonResponse (
    val species: Species,
    val sprites: Sprites,
){
  data class Species(var name: String)
  data class Sprites(var frontDefault: String)
}