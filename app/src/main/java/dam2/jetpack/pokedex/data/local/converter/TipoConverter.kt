package dam2.jetpack.pokedex.data.local.converter

import androidx.room.TypeConverter
import dam2.jetpack.pokedex.domain.model.Tipo


class TipoConverter {
    @TypeConverter
    fun fromTipo(tipo: Tipo): String = tipo.name

    @TypeConverter
    fun toTipo(tipo: String): Tipo = Tipo.valueOf(tipo)
}