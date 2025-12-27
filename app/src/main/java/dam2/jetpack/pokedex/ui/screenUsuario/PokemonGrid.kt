package dam2.jetpack.pokedex.ui.screenUsuario

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dam2.jetpack.pokedex.domain.model.Pokemon

@Composable
fun MostrarListaPokemonGrid(listaPokemon: List<Pokemon>) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        content = {
            items(listaPokemon){pokemon -> // Por cada pokemon llama a mostrarPokemonGrid
                MostrarPokemonGrid(pokemon)
            }
        }
    )
}

@Composable
fun MostrarPokemonGrid(pokemon: Pokemon){
    var mostrar by rememberSaveable { mutableStateOf(false) }

    if (mostrar){
        PokemonAlertDialog(pokemon) {  mostrar = false}
    }
    Card(modifier = Modifier
        .padding(8.dp)
        .height(180.dp).clickable(onClick = {
            mostrar = true
        })
    ){
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(pokemon.imagen),
                contentDescription = pokemon.nombre,
                modifier = Modifier.size(80.dp)
            )

            Column(modifier = Modifier.weight(1f)){
                Text(
                    text = "Nombre: ${pokemon.nombre}", fontSize = 10.sp,
                )
                Text(
                    text = "Tipo: ${pokemon.tipo}", fontSize = 10.sp

                )
                Text(
                    text = "Descripción: ${pokemon.habilidades}", fontSize = 10.sp,
                    maxLines = 3,
                )
            }
        }
    }
}


