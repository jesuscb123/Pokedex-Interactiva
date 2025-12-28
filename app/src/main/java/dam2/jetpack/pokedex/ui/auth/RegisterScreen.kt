package dam2.jetpack.pokedex.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row // Necesario para alinear el RadioButton con su texto
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dam2.jetpack.pokedex.domain.model.Rol // Asegúrate de importar tu Enum Rol

@Composable
fun RegisterScreen(
    viewmodel: AuthViewModel = hiltViewModel(),
    onRegisterSucces: () -> Unit
){
    var nombreUsuario by rememberSaveable { mutableStateOf("") }
    // CAMBIADO: 'var' en vez de 'val' para poder escribir
    var password by rememberSaveable { mutableStateOf("") }

    // CAMBIADO: Variable para guardar el Rol seleccionado. Por defecto ENTRENADOR.
    var selectedRol by rememberSaveable { mutableStateOf(Rol.USER) }

    val state by viewmodel.uiState.collectAsState()

    LaunchedEffect(state.isLogged) {
        val stateActual = state
        if (stateActual.isLogged) onRegisterSucces()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("Registrarse",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(32.dp))

        // USUARIO
        TextField(
            value = nombreUsuario,
            onValueChange = { nombreUsuario = it },
            label = { Text("Nombre de usuario") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        //PASSWORD
        Text("Contraseña")
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text("Selecciona tu Rol:", style = MaterialTheme.typography.titleMedium)

        Rol.entries.forEach { rol ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)

                    .clickable { selectedRol = rol },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                RadioButton(
                    selected = (rol == selectedRol),
                    onClick = { selectedRol = rol }
                )
                Text(
                    text = rol.name,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {

                    viewmodel.register(nombreUsuario, password, selectedRol)
                },

                enabled = nombreUsuario.isNotBlank() && password.isNotBlank()
            ) {
                Text("Crear Cuenta")
            }
        }

        state.error?.let { mensajeError ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = mensajeError, color = Color.Red)
        }
    }
}
