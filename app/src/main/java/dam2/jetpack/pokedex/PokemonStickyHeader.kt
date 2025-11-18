package dam2.jetpack.pokedex

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dam2.jetpack.pokedex.model.Pokemon

@Composable
fun MostrarPokemonStickyHeader(listaPokemon: List<Pokemon>) {
    val pokemonAgrupados: Map<String, List<Pokemon>> = listaPokemon.groupBy { it.tipo }

    LazyColumn(Modifier.fillMaxSize()) {
        pokemonAgrupados.forEach { (tipo, listaPokemon) ->
            stickyHeader {
                Text(text = tipo)
            }

            listaPokemon.forEach { pokemon ->
                item {
                    MostrarPokemon(pokemon)
                }

            }
        }
    }
}