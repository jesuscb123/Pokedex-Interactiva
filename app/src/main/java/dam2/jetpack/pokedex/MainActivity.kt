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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dam2.jetpack.pokedex.domain.model.Pokemon
import dam2.jetpack.pokedex.domain.model.Rol
import dam2.jetpack.pokedex.domain.model.Tipo
import dam2.jetpack.pokedex.ui.auth.AuthScreen
import dam2.jetpack.pokedex.ui.auth.RegisterScreen
import dam2.jetpack.pokedex.ui.home.HomeScreen
import dam2.jetpack.pokedex.ui.screenUsuario.MostrarListaPokemonGrid
import dam2.jetpack.pokedex.ui.screenUsuario.MostrarPokemonLista
import dam2.jetpack.pokedex.ui.screenUsuario.MostrarPokemonStickyHeader
import dam2.jetpack.pokedex.ui.theme.PokedexTheme

@AndroidEntryPoint
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
    var navegadorSegunRol by rememberSaveable { mutableStateOf("") }
    val listaPokemon = listOf(
        Pokemon("Pikachu", Tipo.ELECTRICO, "Usa Impactrueno y ataques eléctricos.", R.drawable.pikachu),
        Pokemon("Bulbasaur", Tipo.PLANTA, "Lanza Látigo Cepa y Drenadoras.", R.drawable.bulbasaur),
        Pokemon("Charmander", Tipo.FUEGO, "Ataca con Ascuas y Furia Dragón.", R.drawable.charmander),
        Pokemon("Squirtle", Tipo.AGUA, "Utiliza Pistola de Agua y se defiende con Refugio.", R.drawable.squirtle),
        Pokemon("Caterpie", Tipo.BICHO, "Su habilidad principal es ralentizar con Disparo Demora.", R.drawable.caterpie),
        Pokemon("Weedle", Tipo.BICHO, "Usa Picotazo Venenoso para envenenar.", R.drawable.weedle),
        Pokemon("Rattata", Tipo.NORMAL, "Conocido por su Ataque Rápido y su agilidad para huir.", R.drawable.rattata),
        Pokemon("Sandshrew", Tipo.TIERRA, "Excava para esquivar y ataca con Arañazo.", R.drawable.sandshrew),
        Pokemon("Nidoran", Tipo.VENENO, "Ataca con Picotazo Venenoso y Punto Tóxico.", R.drawable.nidoran),
        Pokemon("Clefairy", Tipo.HADA, "Usa Destructor y puede dormir a los rivales con Canto.", R.drawable.clefairy)
    )


    val navController = rememberNavController() // Se crea para navegar entre composables solo teniendo un activity.

    PokedexTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = {
                    Text(text = "Pokedex")
                })
            }
            ) { innerPadding ->

            NavHost( // El navHost es el que se encarga de modificar el contenido según el botón pulsado y la ruta seleccionada.
                navController,
                startDestination = "auth",
                modifier = Modifier.padding(innerPadding)) {

                composable("auth"){AuthScreen(
                    onAuthSuccess = { rol ->
                        if (rol == Rol.USER) {
                            navegadorSegunRol = Rol.USER.toString()
                            navController.navigate("home")
                        }
                    },
                    navController = navController
                ) }
                composable("home") { HomeScreen() }
                composable("listaPokemon") { MostrarPokemonLista(listaPokemon) } //llama a las diferentes funciones composables.
                composable("pokemonGrid") { MostrarListaPokemonGrid(listaPokemon) }
                composable("pokemonSticky") { MostrarPokemonStickyHeader(listaPokemon) }
                composable("register") { RegisterScreen(onRegisterSucces = { navController.navigate("auth") }) }

            }
        }
    }
}


