package dam2.jetpack.pokedex.ui.admin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dam2.jetpack.pokedex.domain.model.Pokemon
import dam2.jetpack.pokedex.domain.model.Tipo
import dam2.jetpack.pokedex.domain.repository.IPokemonRepository
import dam2.jetpack.pokedex.domain.usecase.admin.DeletePokemonUseCase
import dam2.jetpack.pokedex.domain.usecase.admin.InsertPokemonUseCase
import dam2.jetpack.pokedex.domain.usecase.admin.UpdatePokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val insertPokemonUseCase: InsertPokemonUseCase,
    private val updatePokemonUseCase: UpdatePokemonUseCase,
    private val deletePokemonUseCase: DeletePokemonUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(AdminUiState())

    val uiState: StateFlow<AdminUiState> = _uiState

    fun actualizarPokemon(nombreOriginal: String, nuevoNombre: String, nuevoTipo: Tipo, nuevasHabilidades: String, imagen: Int) {
        viewModelScope.launch {
            _uiState.value = AdminUiState(isLoading = true)

            if (nombreOriginal == nuevoNombre) {
                val pokemonModificado = Pokemon(nuevoNombre, nuevoTipo, nuevasHabilidades, imagen)
                val result = updatePokemonUseCase(pokemonModificado)
                _uiState.value = result.fold(
                    onSuccess = { AdminUiState(isSuccess = true, mensajeExito = "Actualizado") },
                    onFailure = { AdminUiState(error = it.message) }
                )
            } else {
                deletePokemonUseCase(nombreOriginal)
                insertarPokemon(nuevoNombre, nuevoTipo, nuevasHabilidades, imagen)

            }
        }
    }

    fun insertarPokemon(nombre: String, tipo: Tipo, habilidades: String, imagen: Int){
        viewModelScope.launch {
            _uiState.value = AdminUiState(isLoading = true)

            val pokemonNuevo = Pokemon(
                nombre = nombre,
                tipo = tipo,
                habilidades = habilidades,
                imagen = imagen
            )

            val result = insertPokemonUseCase(pokemonNuevo)

            _uiState.value = result.fold(
                onSuccess = { AdminUiState(isSuccess = true, mensajeExito = "Insertado")},
                onFailure = { AdminUiState(error = it.message)}
            )
        }
    }

}