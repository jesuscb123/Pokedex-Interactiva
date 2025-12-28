package dam2.jetpack.pokedex.data.mapper

import dam2.jetpack.pokedex.data.local.converter.TipoConverter
import dam2.jetpack.pokedex.data.local.entity.PokemonEntity
import dam2.jetpack.pokedex.domain.model.Pokemon

fun PokemonEntity.toDomain(): Pokemon = Pokemon(
    nombre = nombre,
    tipo = TipoConverter().toTipo(tipo),
    habilidades = habilidades,
    imagen = imagenResId
)


fun Pokemon.toEntity(): PokemonEntity {
    return PokemonEntity(
        nombre = this.nombre,
        tipo = this.tipo.name,
        habilidades = this.habilidades,
        imagenResId = this.imagen
    )
}