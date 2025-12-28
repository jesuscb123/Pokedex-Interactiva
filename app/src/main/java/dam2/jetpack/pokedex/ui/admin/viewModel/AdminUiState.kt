package dam2.jetpack.pokedex.ui.admin.viewModel

data class AdminUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,
    val mensajeExito: String? = null
)