package dam2.jetpack.pokedex.ui.admin.screenAdmin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dam2.jetpack.pokedex.domain.model.Tipo
import dam2.jetpack.pokedex.ui.admin.viewModel.AdminViewModel

@Composable
fun InsertarPokemonScreen(
    navController: NavController,
    viewModel: AdminViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    // Variables locales del formulario
    var nombre by rememberSaveable { mutableStateOf("") }
    var habilidades by rememberSaveable { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf(Tipo.NORMAL) } // Valor por defecto
    var expandedTipo by remember { mutableStateOf(false) }

    // Reaccionar al éxito para cerrar la pantalla automáticamente
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.popBackStack()
        }
    }

    // Usamos Column directamente, ya que el Scaffold está en MainActivity
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Margen general
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Empezamos desde arriba
    ) {

        Text(
            text = "Registrar Nuevo Pokémon",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // --- CAMPO NOMBRE ---
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del Pokémon") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- SELECTOR DE TIPO ---
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = tipoSeleccionado.name,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo Elemental") },
                trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                modifier = Modifier.fillMaxWidth()
            )
            // Capa transparente para detectar el click
            Surface(
                modifier = Modifier.matchParentSize(),
                color = Color.Transparent,
                onClick = { expandedTipo = true }
            ) {}

            DropdownMenu(
                expanded = expandedTipo,
                onDismissRequest = { expandedTipo = false },
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Tipo.entries.forEach { tipo ->
                    DropdownMenuItem(
                        text = { Text(text = tipo.name) },
                        onClick = {
                            tipoSeleccionado = tipo
                            expandedTipo = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- CAMPO HABILIDADES ---
        OutlinedTextField(
            value = habilidades,
            onValueChange = { habilidades = it },
            label = { Text("Habilidades / Descripción") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(32.dp))

        // --- BOTÓN DE GUARDAR ---
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    viewModel.insertarPokemon(
                        nombre = nombre,
                        tipo = tipoSeleccionado,
                        habilidades = habilidades,
                        imagen = 0 // Aquí gestionas la imagen por defecto o añades un picker
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = nombre.isNotBlank() && habilidades.isNotBlank()
            ) {
                Text("Guardar Pokémon")
            }
        }

        // --- MENSAJE DE ERROR ---
        state.error?.let { msg ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = msg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
