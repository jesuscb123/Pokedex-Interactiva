package dam2.jetpack.pokedex.domain.usecase

import dam2.jetpack.pokedex.data.local.entity.UsuarioEntity
import dam2.jetpack.pokedex.domain.model.Usuario
import dam2.jetpack.pokedex.domain.repository.IUsuarioRepository
import javax.inject.Inject

class LoginUsuarioUseCase @Inject constructor(
    private val usuarioRepository: IUsuarioRepository
) {

    operator suspend fun invoke(user: String, pass: String): Result<Usuario> {
        return try {
            val usuario = usuarioRepository.login(user, pass)
            if (usuario != null) {
                Result.success(usuario)
            } else {
                Result.failure(Exception("Credenciales incorrectas"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}