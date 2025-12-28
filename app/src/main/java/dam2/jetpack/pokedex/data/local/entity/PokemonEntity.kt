package dam2.jetpack.pokedex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dam2.jetpack.pokedex.domain.model.Tipo

@Entity(tableName = "pokemon_tabla")
data class PokemonEntity(
    @PrimaryKey
    val nombre: String,
    val tipo: Tipo,
    val habilidades: String,
    val imagenResId: Int
)