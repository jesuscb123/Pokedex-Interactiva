package dam2.jetpack.pokedex.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import dam2.jetpack.pokedex.domain.model.Rol

@Composable
fun HomeScreen(navController: NavController, rol: String) {
    val ctx = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Bienvenido a la Pokedex 🐉",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary)

            Button(onClick = { navController.navigate("listaPokemon") }) {
                Text("Mostrar Lista Pokémon")
            }
            Button(onClick = { navController.navigate("pokemonGrid") }) {
                Text("Mostrar Pokémon en Grid")
            }
            Button(onClick = { navController.navigate("pokemonSticky") }) {
                Text("Mostrar Pokémon Sticky Header")
            }
            Button(onClick = { if (rol == Rol.USER.toString()){
                Toast.makeText(ctx, "No tienes permiso para acceder a esta opción", Toast.LENGTH_LONG).show()
            }else{
                navController.navigate("insertarPokemon")
            }
            }) {
                Text("Insertar Pokémon")
            }



    }
}
