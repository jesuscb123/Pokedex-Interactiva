package dam2.jetpack.pokedex.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dam2.jetpack.pokedex.data.local.dao.PokemonDao
import dam2.jetpack.pokedex.data.local.dao.UsuarioDao
import dam2.jetpack.pokedex.data.local.entity.PokemonEntity
import dam2.jetpack.pokedex.data.local.entity.UsuarioEntity

@Database(entities = [
    UsuarioEntity::class,
    PokemonEntity::class
                     ], version = 1)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun PokemonDao(): PokemonDao
}
