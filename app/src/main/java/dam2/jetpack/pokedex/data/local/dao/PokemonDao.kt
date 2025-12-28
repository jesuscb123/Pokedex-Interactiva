package dam2.jetpack.pokedex.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dam2.jetpack.pokedex.data.local.entity.PokemonEntity

interface PokemonDao {
    @Query("SELECT * FROM pokemon_tabla")
    suspend fun getAllPokemons(): List<PokemonEntity>


    @Query("SELECT * FROM pokemon_tabla WHERE nombre = :nombre")
    suspend fun getPokemonPorNombre(nombre: String): PokemonEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonEntity)

    @Update
    suspend fun updatePokemon(pokemon: PokemonEntity)

    // Borrar
    @Query("DELETE FROM pokemon_tabla WHERE nombre = :nombre")
    suspend fun deletePokemon(nombre: String)
}