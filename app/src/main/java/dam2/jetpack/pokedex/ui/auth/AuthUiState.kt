package dam2.jetpack.pokedex.ui.auth

import dam2.jetpack.pokedex.domain.model.Rol

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val rol: Rol? = null,
    val isLogged: Boolean = false
)
