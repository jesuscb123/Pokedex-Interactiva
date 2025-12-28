package dam2.jetpack.pokedex.domain.usecase.usuarios

import dam2.jetpack.pokedex.domain.model.Usuario
import dam2.jetpack.pokedex.domain.repository.IUsuarioRepository
import javax.inject.Inject

class LoginUsuarioUseCase @Inject constructor(
    private val usuarioRepository: IUsuarioRepository
) {
    suspend operator fun invoke(
        nombreUsuario: String,
        password: String
    ): Result<Usuario> {
        return usuarioRepository.login(nombreUsuario, password)
    }
}
