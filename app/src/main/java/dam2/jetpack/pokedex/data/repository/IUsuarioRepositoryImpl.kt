package dam2.jetpack.pokedex.data.repository

import dam2.jetpack.pokedex.data.local.dao.UsuarioDao
import dam2.jetpack.pokedex.data.mapper.toDomain
import dam2.jetpack.pokedex.data.mapper.toEntity
import dam2.jetpack.pokedex.data.security.PasswordHasher
import dam2.jetpack.pokedex.domain.model.Usuario
import dam2.jetpack.pokedex.domain.repository.IUsuarioRepository
import javax.inject.Inject

class IUsuarioRepositoryImpl @Inject constructor(
    private val usuarioDao: UsuarioDao
) : IUsuarioRepository {
    override suspend fun login(
        nombreUsuario: String,
        password: String
    ): Usuario? {
        val salt = PasswordHasher.generateSalt()
        val hash = PasswordHasher.hash(password, salt)

        return usuarioDao.login(nombreUsuario, hash)
            ?.toDomain()
    }

    override suspend fun register(
        usuario: Usuario,
        password: String
    ) {
        val salt = PasswordHasher.generateSalt()
        val hash = PasswordHasher.hash(password, salt)
        usuarioDao.insert(usuario.toEntity(hash))

    }

}