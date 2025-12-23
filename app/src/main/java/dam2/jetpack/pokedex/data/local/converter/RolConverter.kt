package dam2.jetpack.pokedex.data.local.converter

import androidx.room.TypeConverter
import dam2.jetpack.pokedex.model.Rol

class RolConverter {
    @TypeConverter
    fun fromRol(rol: Rol): String = rol.name

    @TypeConverter
    fun toRol(valor: String): Rol = Rol.valueOf(valor)
}