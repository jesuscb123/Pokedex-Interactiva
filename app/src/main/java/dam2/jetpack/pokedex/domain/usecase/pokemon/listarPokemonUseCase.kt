package dam2.jetpack.pokedex.domain.usecase.pokemon

import dam2.jetpack.pokedex.domain.model.Pokemon
import dam2.jetpack.pokedex.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class listarPokemonUseCase @Inject constructor(
    private val pokemonRepository: IPokemonRepository
) {
    suspend operator fun invoke(): Flow<List<Pokemon>>{
        return pokemonRepository.getAllPokemons()
    }
}