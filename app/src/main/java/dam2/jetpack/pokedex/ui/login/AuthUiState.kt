package dam2.jetpack.pokedex.ui.login

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLogged: Boolean = false
)
