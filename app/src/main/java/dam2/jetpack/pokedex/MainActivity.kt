package dam2.jetpack.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dam2.jetpack.pokedex.model.Pokemon
import dam2.jetpack.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IniciarApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IniciarApp(){
    val listaPokemon = listOf<Pokemon>(
        Pokemon("Pikachu", "Electrico", "Pikachu", R.drawable.pikachu),
        Pokemon("Bulbasaur", "Planta", "Bulbasaur", R.drawable.bulbasaur),
        Pokemon("Charmander", "Fuego", "Charmander", R.drawable.charmander),
        Pokemon("Squirtle", "Agua", "Squirtle", R.drawable.squirtle),
        Pokemon("Caterpie", "Bicho", "Caterpie", R.drawable.caterpie),
        Pokemon("Weedle", "Bicho", "Weedle", R.drawable.weedle),
        Pokemon("Rattata", "Normal", "Rattata", R.drawable.rattata),
        Pokemon("Sandshrew", "Tierra", "Sandshrew", R.drawable.sandshrew),
        Pokemon("Nidoran", "Veneno", "Nidoran", R.drawable.nidoran),
        Pokemon("Clefairy", "Hada", "Clefairy", R.drawable.clefairy),
    )

    val navController = rememberNavController()

    PokedexTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = {
                    Text(text = "Pokedex")
                })
            },
            bottomBar = {
                BottomAppBar {
                    NavigationBar {
                        BotonesNavigation("listaPokemon", navController)
                        BotonesNavigation("pokemonGrid", navController)
                        BotonesNavigation("pokemonSticky", navController)
                    }
                }
            }
            ) { innerPadding ->

            NavHost(
                navController,
                startDestination = "listaPokemon",
                modifier = Modifier.padding(innerPadding)) {

                composable("listaPokemon") {MostrarPokemonLista(listaPokemon)}
                composable("pokemonGrid") {MostrarListaPokemonGrid(listaPokemon)}
                composable("pokemonSticky") {MostrarPokemonStickyHeader(listaPokemon)}
            }
        }
    }
}


@Composable
fun RowScope.BotonesNavigation(ruta: String, navController: NavController){
    NavigationRailItem(
        selected = false,
        onClick = {
            navController.navigate(ruta)
        },
        icon = {
            Text(ruta)
        }
    )
}

