package dam2.jetpack.pokedex.domain.repository

import dam2.jetpack.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    suspend fun getAllPokemons(): Flow<List<Pokemon>>
    suspend fun getPokemonByName(nombre: String): Result<Pokemon?>
    suspend fun insertPokemon(pokemon: Pokemon): Result<Unit>
    suspend fun updatePokemon(pokemon: Pokemon): Result<Unit>
    suspend fun deletePokemon(nombre: String): Result<Unit>
}