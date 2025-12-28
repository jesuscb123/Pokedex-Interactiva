package dam2.jetpack.pokedex.domain.usecase.usuarios

import dam2.jetpack.pokedex.domain.repository.IUsuarioRepository
import javax.inject.Inject

class VerificarExisteUsuarioUseCase @Inject constructor(
    private val usuarioRepository: IUsuarioRepository
) {
    suspend operator fun invoke(nombreUsuario: String): Result<Unit>{
        return usuarioRepository.verificarUsuarioExiste(nombreUsuario)
    }
}