package dam2.jetpack.pokedex.ui.usuarios.screenUsuario

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dam2.jetpack.pokedex.domain.model.Pokemon
import dam2.jetpack.pokedex.domain.model.Tipo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MostrarPokemonStickyHeader(listaPokemon: List<Pokemon>) {
    val pokemonAgrupados: Map<Tipo, List<Pokemon>> = listaPokemon.groupBy { it.tipo }

    LazyColumn(Modifier.fillMaxSize()) {
        pokemonAgrupados.forEach { (tipo, listaPokemon) ->
            stickyHeader() {
                Text(text = tipo.tipoNombre, modifier = Modifier.background(tipo.Color).fillMaxWidth())
            }

            listaPokemon.forEach { pokemon ->
                item {
                    MostrarPokemon(pokemon)
                }

            }
        }
    }
}