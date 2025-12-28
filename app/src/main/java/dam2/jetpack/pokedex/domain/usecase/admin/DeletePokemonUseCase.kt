package dam2.jetpack.pokedex.domain.usecase.admin

import dam2.jetpack.pokedex.domain.model.Pokemon
import dam2.jetpack.pokedex.domain.repository.IPokemonRepository
import javax.inject.Inject

class DeletePokemonUseCase @Inject constructor(
    private val pokemonRepository: IPokemonRepository
) {
    suspend operator fun invoke(nombrePokemon: String): Result<Unit> {
        return pokemonRepository.deletePokemon(nombrePokemon)
    }
}