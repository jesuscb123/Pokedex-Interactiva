package dam2.jetpack.pokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dam2.jetpack.pokedex.data.local.entity.UsuarioEntity

@Dao
interface UsuarioDao{

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert (user: UsuarioEntity): Long

    @Query("""
        SELECT * FROM usuarios 
        WHERE nombreUsuario = :nombreUsuario
        LIMIT 1
    """)
    suspend fun findByNombreUsuario(nombreUsuario: String): UsuarioEntity?

    @Query("""
        SELECT * FROM usuarios
        WHERE nombreUsuario = :nombreUsuario 
        AND passwordHash = :password
        LIMIT 1
    """)
    suspend fun login(nombreUsuario: String, password: String): UsuarioEntity?

    @Query("SELECT * FROM usuarios")
    suspend fun getALL(): List<UsuarioEntity>

    @Delete
    suspend fun delete(usuario: UsuarioEntity)

}