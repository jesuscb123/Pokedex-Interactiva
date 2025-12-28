package dam2.jetpack.pokedex.data.mapper

import dam2.jetpack.pokedex.data.local.entity.PokemonEntity
import dam2.jetpack.pokedex.domain.model.Pokemon

fun PokemonEntity.toDomain(): Pokemon = Pokemon(
    nombre = nombre,
    tipo = tipo,
    habilidades = habilidades,
    imagen = imagenResId
)


fun Pokemon.toEntity(): PokemonEntity {
    return PokemonEntity(
        nombre = this.nombre,
        tipo = this.tipo,
        habilidades = this.habilidades,
        imagenResId = this.imagen
    )
}