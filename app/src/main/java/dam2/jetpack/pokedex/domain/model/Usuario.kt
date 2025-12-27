package dam2.jetpack.pokedex.domain.model

data class Usuario(
    val id: Int,
    val nombreUsuario: String,
    val rol: Rol
)