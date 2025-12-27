package dam2.jetpack.pokedex.domain.usecase

import dam2.jetpack.pokedex.domain.repository.IUsuarioRepository
import javax.inject.Inject

class RegisterUsuarioUseCase @Inject constructor(
    private val usuarioRepository: IUsuarioRepository
) {
    suspend operator fun invoke(
        nombreUsuario: String,
        password: String
    ): Result<Unit> {
       return usuarioRepository.register(nombreUsuario, password)
    }


}