package dam2.jetpack.pokedex.domain.repository

import dam2.jetpack.pokedex.domain.model.Usuario

interface IUsuarioRepository {
    suspend fun login(
        nombreUsuario: String,
        password: String
    ): Usuario?

    suspend fun register(
        nombreUsuario: String,
        password: String
    ): Result<Unit>
}