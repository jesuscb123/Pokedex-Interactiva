package dam2.jetpack.pokedex.di

import dam2.jetpack.pokedex.data.repository.UsuarioRepositoryImpl
import dam2.jetpack.pokedex.domain.repository.IUsuarioRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dam2.jetpack.pokedex.data.repository.PokemonRepositoryImpl
import dam2.jetpack.pokedex.domain.repository.IPokemonRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUsuarioRepository(
        impl: UsuarioRepositoryImpl
    ): IUsuarioRepository

    @Binds
    abstract fun bindPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): IPokemonRepository
}
