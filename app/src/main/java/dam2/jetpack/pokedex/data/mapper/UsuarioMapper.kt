package dam2.jetpack.pokedex.data.mapper

import dam2.jetpack.pokedex.data.local.entity.UsuarioEntity
import dam2.jetpack.pokedex.domain.model.Usuario

fun UsuarioEntity.toDomain(): Usuario =
    Usuario(
        id = id,
        nombreUsuario = nombreUsuario,
        rol = rol
    )

fun Usuario.toEntity(passwordHash: String): UsuarioEntity =
    UsuarioEntity(
        id = id,
        nombreUsuario = nombreUsuario,
        passwordHash = passwordHash,
        rol = rol
    )
