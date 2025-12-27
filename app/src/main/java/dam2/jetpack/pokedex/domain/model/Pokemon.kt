package dam2.jetpack.pokedex.domain.model

data class Pokemon(
    val nombre: String,
    val tipo: Tipo,
    val habilidades: String,
    val imagen: Int) {
}