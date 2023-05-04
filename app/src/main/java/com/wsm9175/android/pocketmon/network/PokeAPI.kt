package com.wsm9175.android.pocketmon.network

import com.wsm9175.android.pocketmon.domain.PokemonResponse
import com.wsm9175.android.pocketmon.network.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeAPI {
    @GET("pokemon/")
    suspend fun getPokemons(): Response

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Response

    @GET("pokemon/{pid}/")
    suspend fun getPokemon(@Path("pid") pid: Int): PokemonResponse
}