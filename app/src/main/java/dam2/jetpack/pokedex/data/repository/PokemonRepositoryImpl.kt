package dam2.jetpack.pokedex.data.repository

import dam2.jetpack.pokedex.data.local.dao.PokemonDao
import dam2.jetpack.pokedex.data.local.entity.PokemonEntity
import dam2.jetpack.pokedex.data.mapper.toDomain
import dam2.jetpack.pokedex.data.mapper.toEntity
import dam2.jetpack.pokedex.domain.model.Pokemon
import dam2.jetpack.pokedex.domain.model.Tipo
import dam2.jetpack.pokedex.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDao: PokemonDao
) : IPokemonRepository {

    override suspend fun getAllPokemons(): Flow<List<Pokemon>> {
        val listaPokemon = pokemonDao.getAllPokemons()

        return listaPokemon.map { lista ->
            lista.map { it.toDomain() }
        }
    }

    override suspend fun getPokemonByName(nombre: String): Result<Pokemon?> {

        val pokemonEncontrado = pokemonDao.getPokemonPorNombre(nombre)

        if (pokemonEncontrado == null) return Result.failure(Exception("Pokemon no encontrado"))

        return Result.success(pokemonEncontrado.toDomain())
    }

    override suspend fun insertPokemon(pokemon: Pokemon): Result<Unit> {

        pokemonDao.insertPokemon(pokemon.toEntity())

        return Result.success(Unit)
    }

    override suspend fun updatePokemon(pokemon: Pokemon): Result<Unit> {

        pokemonDao.updatePokemon(pokemon.toEntity())

        return Result.success(Unit)
    }

    override suspend fun deletePokemon(nombre: String): Result<Unit> {

        val pokemonEncontrado = pokemonDao.getPokemonPorNombre(nombre)

        if (pokemonEncontrado == null) return Result.failure(Exception("El pokémon que quieres eliminar no existe."))

        return Result.success(Unit)
    }


}
