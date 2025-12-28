package dam2.jetpack.pokedex.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dam2.jetpack.pokedex.domain.model.Rol

@Composable
fun HomeScreen(navController: NavController, rol: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bienvenido a la Pokedex 🐉")
        if (rol == Rol.USER.toString()) {
            Button(onClick = { navController.navigate("listaPokemon") }) {
                Text("Mostrar Lista Pokémon")
            }
            Button(onClick = { navController.navigate("pokemonGrid") }) {
                Text("Mostrar Pokémon en Grid")
            }
            Button(onClick = { navController.navigate("pokemonSticky") }) {
                Text("Mostrar Pokémon Sticky Header")
            }

        }else{

        }

    }
}
