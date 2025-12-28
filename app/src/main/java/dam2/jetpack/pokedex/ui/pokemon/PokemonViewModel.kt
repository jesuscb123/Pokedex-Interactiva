package dam2.jetpack.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dam2.jetpack.pokedex.domain.model.Pokemon
import dam2.jetpack.pokedex.domain.usecase.pokemon.listarPokemonUseCase
import dam2.jetpack.pokedex.ui.pokemon.PokemonListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Estado de la UI para la lista de Pokémon


@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val listarPokemonUseCase: listarPokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

    init {

        cargarPokemons()
    }

    fun cargarPokemons() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val resultado = listarPokemonUseCase()

            resultado.fold(
                onSuccess = { lista ->
                    _uiState.value = PokemonListUiState(
                        isLoading = false,
                        pokemons = lista
                    )
                },
                onFailure = { error ->
                    _uiState.value = PokemonListUiState(
                        isLoading = false,
                        error = error.message ?: "Error desconocido"
                    )
                }
            )
        }
    }
}
