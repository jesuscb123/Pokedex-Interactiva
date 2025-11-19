package dam2.jetpack.pokedex.model

import androidx.compose.ui.graphics.Color

enum class Tipo(var tipoNombre: String, var Color: Color) {
    ELECTRICO("Eléctrico", Color(0xFFECE126)),
    PLANTA("Planta", Color(0xFF4CAF50)),

    FUEGO("Fuego", Color(0xFFF44336)),
    AGUA("Agua", Color(0xFF3F51B5)),
    BICHO("Bicho", Color(0xFF044907)),
    NORMAL("Normal", Color(0xFF9E9E9E)),
    TIERRA("Tierra", Color(0xFF795548)),
    HADA("Hada", Color(0xFFB79356)),
    VENENO("Veneno", Color(0xFF6A1B9A))
}