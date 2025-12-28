package dam2.jetpack.pokedex.ui.pokemon

import dam2.jetpack.pokedex.domain.model.Pokemon

data class PokemonListUiState(
    val isLoading: Boolean = false,
    val pokemons: List<Pokemon> = emptyList(),
    val error: String? = null
)