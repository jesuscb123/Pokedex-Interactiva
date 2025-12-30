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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dam2.jetpack.pokedex.domain.model.Pokemon
import dam2.jetpack.pokedex.domain.model.Rol
import dam2.jetpack.pokedex.domain.model.Tipo
import dam2.jetpack.pokedex.presentation.viewmodel.PokemonViewModel
import dam2.jetpack.pokedex.ui.admin.screenAdmin.InsertarPokemonScreen
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
fun IniciarApp(
    pokemonViewModel: PokemonViewModel = hiltViewModel()
){
    val state by pokemonViewModel.uiState.collectAsState()
    val listaPokemon = state.pokemons
    var rolActual by rememberSaveable { mutableStateOf("") }


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
                            rolActual = Rol.USER.toString()

                        }else{
                            rolActual = Rol.ADMIN.toString()
                        }
                        navController.navigate("home")
                    },
                    navController = navController
                ) }
                composable("home") { HomeScreen(navController, rolActual) }
                composable("listaPokemon") { MostrarPokemonLista(listaPokemon) } //llama a las diferentes funciones composables.
                composable("pokemonGrid") { MostrarListaPokemonGrid(listaPokemon) }
                composable("pokemonSticky") { MostrarPokemonStickyHeader(listaPokemon) }
                composable("register") { RegisterScreen(onRegisterSucces = { navController.navigate("auth") }) }
                composable("insertarPokemon") { InsertarPokemonScreen(navController = navController) }

            }
        }
    }
}


