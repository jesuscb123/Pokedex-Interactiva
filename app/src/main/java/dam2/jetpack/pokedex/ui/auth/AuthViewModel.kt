package dam2.jetpack.pokedex.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dam2.jetpack.pokedex.domain.model.Rol
import dam2.jetpack.pokedex.domain.usecase.usuarios.LoginUsuarioUseCase
import dam2.jetpack.pokedex.domain.usecase.usuarios.RegisterUsuarioUseCase
import dam2.jetpack.pokedex.domain.usecase.usuarios.VerificarExisteUsuarioUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUsuarioUseCase: LoginUsuarioUseCase,
    private val registerUsuarioUseCase: RegisterUsuarioUseCase,
    private val verificarExisteUsuarioUseCase: VerificarExisteUsuarioUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(nombreUsuario: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)

            val result = loginUsuarioUseCase(nombreUsuario, password)

            _uiState.value = result.fold(
                onSuccess = {usuario ->
                    AuthUiState(isLogged = true, rol = usuario.rol)
                },
                onFailure = { AuthUiState(error = it.message) }
            )
        }
    }

    fun register(nombreUsuario: String, password: String, rol: Rol) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)

            val usuarioExiste = verificarExisteUsuarioUseCase(nombreUsuario)

           _uiState.value =  usuarioExiste.fold(
                onSuccess = {
                    val result = registerUsuarioUseCase(nombreUsuario, password, rol)

                    result.fold(
                        onSuccess = { AuthUiState(isLogged = true, rol = rol) },
                        onFailure = { AuthUiState(error = it.message) }
                    )
                },
                onFailure = {AuthUiState(error = it.message)}
            )


        }
    }


    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
