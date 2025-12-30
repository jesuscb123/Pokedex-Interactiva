package dam2.jetpack.pokedex.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dam2.jetpack.pokedex.R
import dam2.jetpack.pokedex.domain.model.Pokemon
import dam2.jetpack.pokedex.domain.model.Tipo
import dam2.jetpack.pokedex.domain.usecase.admin.InsertPokemonUseCase
import dam2.jetpack.pokedex.domain.usecase.pokemon.listarPokemonUseCase
import dam2.jetpack.pokedex.ui.pokemon.PokemonListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

// Estado de la UI para la lista de Pokémon


@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val listarPokemonUseCase: listarPokemonUseCase,
    private val insertPokemonUseCase: InsertPokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

    init {

        cargarPokemonsIniciales()
        cargarPokemons()
    }

    fun cargarPokemons() {
        viewModelScope.launch {
            listarPokemonUseCase().collect(
                { lista ->
                    _uiState.value = PokemonListUiState(isLoading = false, pokemons = lista)
                }
            )
        }
    }

    fun cargarPokemonsIniciales() {
        viewModelScope.launch {
            listarPokemonUseCase()
                .take(1)
                .collect { lista ->
                    if (lista.isEmpty()) {
                        insertarPokemonsIniciales()
                    }
                }
        }
    }

    private suspend fun insertarPokemonsIniciales() {
        val pokemonIniciales = listOf(
            Pokemon("Bulbasaur", Tipo.PLANTA, "Espesura", R.drawable.bulbasaur),
            Pokemon("Charmander", Tipo.FUEGO, "Mar Llamas", R.drawable.charmander),
            Pokemon("Squirtle", Tipo.AGUA, "Torrente", R.drawable.squirtle),
            Pokemon("Caterpie", Tipo.BICHO, "Polvo Escudo", R.drawable.caterpie),
            Pokemon("Weedle", Tipo.BICHO, "Polvo Escudo", R.drawable.weedle),
            Pokemon("Pikachu", Tipo.ELECTRICO, "Electricidad Estática", R.drawable.pikachu),
            Pokemon("Rattata", Tipo.NORMAL, "Fuga, Agallas", R.drawable.rattata),
            Pokemon("Sandshrew", Tipo.TIERRA, "Velo Arena", R.drawable.sandshrew),
            Pokemon("Nidoran♀", Tipo.VENENO, "Punto Tóxico", R.drawable.nidoran),
            Pokemon("Clefairy", Tipo.HADA, "Gran Encanto", R.drawable.clefairy)
        )

        pokemonIniciales.forEach { pokemon ->
            insertPokemonUseCase(pokemon)
        }
    }
}
