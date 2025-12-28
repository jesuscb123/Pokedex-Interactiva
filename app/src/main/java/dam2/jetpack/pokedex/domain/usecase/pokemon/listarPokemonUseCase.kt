package dam2.jetpack.pokedex.domain.usecase.pokemon

import dam2.jetpack.pokedex.domain.model.Pokemon
import dam2.jetpack.pokedex.domain.repository.IPokemonRepository
import javax.inject.Inject

class listarPokemonUseCase @Inject constructor(
    private val pokemonRepository: IPokemonRepository
) {
    suspend operator fun invoke(): Result<List<Pokemon>>{
        return pokemonRepository.getAllPokemons()
    }
}