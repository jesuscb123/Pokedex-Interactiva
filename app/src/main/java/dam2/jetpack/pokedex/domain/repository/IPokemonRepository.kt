package dam2.jetpack.pokedex.domain.repository

import dam2.jetpack.pokedex.domain.model.Pokemon

interface IPokemonRepository {
    suspend fun getAllPokemons(): Result<List<Pokemon>>
    suspend fun getPokemonByName(nombre: String): Result<Pokemon?>
    suspend fun insertPokemon(pokemon: Pokemon): Result<Unit>
    suspend fun updatePokemon(pokemon: Pokemon): Result<Unit>
    suspend fun deletePokemon(nombre: String): Result<Unit>
}