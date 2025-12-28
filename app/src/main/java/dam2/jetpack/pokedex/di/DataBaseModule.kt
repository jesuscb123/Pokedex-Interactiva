package dam2.jetpack.pokedex.di

import android.content.Context
import androidx.room.Room
import dam2.jetpack.pokedex.data.local.dao.UsuarioDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dam2.jetpack.pokedex.data.local.dao.PokemonDao
import dam2.jetpack.pokedex.data.local.database.PokedexDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PokedexDatabase =
        Room.databaseBuilder(
            context,
            PokedexDatabase::class.java,
            "pokedex_db"
        ).build()

    @Provides
    fun provideUsuarioDao(
        database: PokedexDatabase): UsuarioDao = database.usuarioDao()

    @Provides
    fun providePokemonDao(database: PokedexDatabase): PokemonDao = database.PokemonDao()
}
