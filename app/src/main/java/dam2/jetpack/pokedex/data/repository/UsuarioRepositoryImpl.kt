package dam2.jetpack.pokedex.data.repository

import dam2.jetpack.pokedex.data.local.dao.UsuarioDao
import dam2.jetpack.pokedex.data.local.entity.UsuarioEntity
import dam2.jetpack.pokedex.data.mapper.toDomain
import dam2.jetpack.pokedex.data.security.PasswordHasher
import dam2.jetpack.pokedex.domain.model.Rol
import dam2.jetpack.pokedex.domain.model.Usuario
import dam2.jetpack.pokedex.domain.repository.IUsuarioRepository
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    private val usuarioDao: UsuarioDao
) : IUsuarioRepository {

    override suspend fun login(
        nombreUsuario: String,
        password: String
    ): Result<Usuario> {

        val usuarioEntity = usuarioDao.findByNombreUsuario(nombreUsuario)
            ?: return Result.failure(Exception("El usuario no existe"))

        val isValid = PasswordHasher.verify(
            password = password,
            hash = usuarioEntity.passwordHash
        )

        if (!isValid) return Result.failure(Exception("Error al iniciar sesión"))

        return Result.success(usuarioEntity.toDomain())
    }

    override suspend fun register(
        nombreUsuario: String,
        password: String
    ): Result<Unit> {

        val hash = PasswordHasher.hash(password)

        usuarioDao.insert(
            UsuarioEntity(
                nombreUsuario = nombreUsuario,
                passwordHash = hash,
                rol = Rol.USER
            )
        )

        return Result.success(Unit)
    }
}
