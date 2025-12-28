package dam2.jetpack.pokedex.domain.repository

import dam2.jetpack.pokedex.data.local.entity.UsuarioEntity
import dam2.jetpack.pokedex.domain.model.Rol
import dam2.jetpack.pokedex.domain.model.Usuario

interface IUsuarioRepository {
    suspend fun login(
        nombreUsuario: String,
        password: String
    ): Result<Usuario>

    suspend fun register(
        nombreUsuario: String,
        password: String,
        rol: Rol
    ): Result<Unit>

    suspend fun verificarUsuarioExiste(
        nombreUsuario: String
    ): Result<Unit>
}