package dam2.jetpack.pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dam2.jetpack.pokedex.model.Pokemon

@Composable
fun MostrarPokemonLista(listaPokemon: List<Pokemon>){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        listaPokemon.forEach { pokemon ->
            item {
                MostrarPokemon(pokemon)
                Spacer(Modifier.padding(5.dp))
                HorizontalDivider()
                Spacer(Modifier.padding(5.dp))
            }
        }
    }
}

@Composable
fun MostrarPokemon(pokemon: Pokemon){
    var mostrar by rememberSaveable { mutableStateOf(false) }

    if (mostrar){
        PokemonAlertDialog(
            pokemon = pokemon,
            onDismissRequest = { mostrar = false }
        )
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = {
            mostrar = true
        })){
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Image(
                painter = painterResource(pokemon.imagen),
                contentDescription = pokemon.nombre,
                modifier = Modifier.size(60.dp))

            Column(modifier = Modifier.padding(10.dp)){
                Text(text = "Nombre: ${pokemon.nombre}")
                Text(text = "Tipo: ${pokemon.tipo}")
                Text(text = "Descripción: ${pokemon.habilidades}")
            }
        }


    }
}

@Composable
fun PokemonAlertDialog(
    pokemon: Pokemon,
    onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,

        title = {
            Text(
                text = pokemon.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Image(
                    painter = painterResource(id = pokemon.imagen),
                    contentDescription = "Imagen de ${pokemon.nombre}",
                    modifier = Modifier.size(150.dp)
                )

                Text(text = "Tipo: ${pokemon.tipo}", fontSize = 18.sp)

                Text(text = pokemon.habilidades, textAlign = TextAlign.Justify)
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text("Cerrar")
            }
        }
    )
}