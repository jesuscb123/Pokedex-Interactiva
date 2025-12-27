package dam2.jetpack.pokedex.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dam2.jetpack.pokedex.domain.usecase.LoginUsuarioUseCase
import dam2.jetpack.pokedex.domain.usecase.RegisterUsuarioUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUserUseCase: LoginUsuarioUseCase,
    private val registerUserUseCase: RegisterUsuarioUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)

            val result = loginUserUseCase(username, password)

            _uiState.value = result.fold(
                onSuccess = {usuario ->
                    AuthUiState(isLogged = true, rol = usuario.rol)
                },
                onFailure = { AuthUiState(error = it.message) }
            )
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)

            val result = registerUserUseCase(username, password)

            _uiState.value = result.fold(
                onSuccess = { AuthUiState(isLogged = true) },
                onFailure = { AuthUiState(error = it.message) }
            )
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
